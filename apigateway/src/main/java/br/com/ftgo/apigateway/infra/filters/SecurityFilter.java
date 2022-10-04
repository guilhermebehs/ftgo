package br.com.ftgo.apigateway.infra.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityFilter implements GlobalFilter {

    @Value("${basic-auth}")
    private String basicAuth;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if(!basicAuthIsValid(exchange)){

            var response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();

        }

        return chain.filter(exchange);

    }

    private boolean basicAuthIsValid(ServerWebExchange exchange){

        var basicAuthHeaders = exchange.getRequest().getHeaders().get("Authorization");
        if(basicAuthHeaders == null || basicAuthHeaders.isEmpty())
            return false;

        var basicAuthReceived = basicAuthHeaders.get(0).replace("Basic ", "");

        return basicAuthReceived.equals(basicAuth);

    }
}
