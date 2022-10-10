package br.com.guilhermebehs.ftgo.order.adapters.in.http;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.CreateOrderDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderDetailsDto;
import br.com.guilhermebehs.ftgo.order.domain.services.CreateOrderService;
import br.com.guilhermebehs.ftgo.order.domain.services.GetOrderDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class HttpOrder {

    private final CreateOrderService createOrderService;
    private final GetOrderDetailsService getOrderDetailsService;

    public HttpOrder(CreateOrderService createOrderService, GetOrderDetailsService getOrderDetailsService) {
        this.createOrderService = createOrderService;
        this.getOrderDetailsService = getOrderDetailsService;
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateOrderDto createOrderDto){

        var orderId = createOrderService.create(createOrderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable("id") String id){
        return ResponseEntity.ok(getOrderDetailsService.get(id));
    }
}
