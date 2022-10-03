package br.com.guilhermebehs.ftgo.order.adapters.out.http;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.FindProductDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.InternalErrorException;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;

@Service
public class HttpKitchenService implements KitchenService {

    private final Logger logger = LoggerFactory.getLogger(HttpKitchenService.class);


    @Value("${kitchen-ms.host}")
    private String kitchenMsHost;


    private final RestTemplate restTemplate;

    public HttpKitchenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<FindProductDto> getProductByNameAndKitchen(String name, String kitchen) {

        var uri = UriComponentsBuilder.fromUriString(kitchenMsHost)
                .pathSegment("products")
                .pathSegment(name)
                .pathSegment("kitchens")
                .pathSegment(kitchen)
                .build().toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            var findProductDto = restTemplate
                    .exchange(uri,GET, new HttpEntity<>(headers), FindProductDto.class)
                    .getBody();

            return findProductDto == null ?
                    Optional.empty() : Optional.of(findProductDto);
        }
        catch (HttpClientErrorException e) {
            return Optional.empty();
        }

        catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalErrorException();
        }

    }

    @Override
    public void bookProductAmount(String name, String kitchen, int amount) {

        var uri = UriComponentsBuilder.fromUriString(kitchenMsHost)
                .pathSegment("products")
                .pathSegment(name)
                .pathSegment("kitchens")
                .pathSegment(kitchen)
                .pathSegment("amount")
                .pathSegment(String.valueOf(amount))
                .pathSegment("book-amount")
                .build().toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            var findProductDto = restTemplate
                    .exchange(uri,PATCH, new HttpEntity<>(headers), FindProductDto.class)
                    .getBody();
        }

        catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalErrorException();
        }

    }

    @Override
    public void confirmBookedProductAmount(String name, String kitchen, int amount) {

        var uri = UriComponentsBuilder.fromUriString(kitchenMsHost)
                .pathSegment("products")
                .pathSegment(name)
                .pathSegment("kitchens")
                .pathSegment(kitchen)
                .pathSegment("amount")
                .pathSegment(String.valueOf(amount))
                .pathSegment("confirm-booked-amount")
                .build().toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
             restTemplate
                    .exchange(uri,PATCH, new HttpEntity<>(headers), FindProductDto.class)
                    .getBody();
        }

        catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalErrorException();
        }
    }


    @Override
    public void cancelBookedProductAmount(String name, String kitchen, int amount) {

        var uri = UriComponentsBuilder.fromUriString(kitchenMsHost)
                .pathSegment("products")
                .pathSegment(name)
                .pathSegment("kitchens")
                .pathSegment(kitchen)
                .pathSegment("amount")
                .pathSegment(String.valueOf(amount))
                .pathSegment("cancel-booked-amount")
                .build().toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
           restTemplate
                    .exchange(uri, PATCH, new HttpEntity<>(headers), FindProductDto.class)
                    .getBody();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InternalErrorException();
        }
    }


}
