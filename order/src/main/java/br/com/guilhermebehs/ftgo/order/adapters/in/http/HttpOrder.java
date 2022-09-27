package br.com.guilhermebehs.ftgo.order.adapters.in.http;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.CreateOrderDto;
import br.com.guilhermebehs.ftgo.order.domain.services.CreateOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class HttpOrder {

    private final CreateOrderService createOrderService;

    public HttpOrder(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateOrderDto createOrderDto){

        var orderId = createOrderService.create(createOrderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
