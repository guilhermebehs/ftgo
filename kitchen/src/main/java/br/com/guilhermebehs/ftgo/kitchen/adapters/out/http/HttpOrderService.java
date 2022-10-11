package br.com.guilhermebehs.ftgo.kitchen.adapters.out.http;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.dtos.OrderDetailsDto;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.InternalErrorException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpMethod.GET;

@Service
public class HttpOrderService implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(HttpOrderService.class);


    @Value("${order-ms.host}")
    private String orderMsHost;


    private final RestTemplate restTemplate;

    public HttpOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public OrderDetailsDto getOrderDetails(String id) {
        var uri = UriComponentsBuilder.fromUriString(orderMsHost)
                .pathSegment("orders")
                .pathSegment(id)
                .pathSegment("details")
                .build().toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return restTemplate
                    .exchange(uri, GET, new HttpEntity<>(headers), OrderDetailsDto.class)
                    .getBody();

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalErrorException();
        }
    }
}
