package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http;

import br.com.guilhermebehs.ftgo.kitchen.domain.services.ConfirmOrderService;
import br.com.guilhermebehs.ftgo.kitchen.domain.services.DenyOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class HttpOrder {

    private final ConfirmOrderService confirmOrderService;
    private final DenyOrderService denyOrderService;

    public HttpOrder(ConfirmOrderService confirmOrderService,
                     DenyOrderService denyOrderService) {
        this.confirmOrderService = confirmOrderService;
        this.denyOrderService = denyOrderService;
    }


    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<Void> confirmOrder(@PathVariable("orderId") String orderId){
        confirmOrderService.confirm(orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{orderId}/deny")
    public ResponseEntity<Void> denyOrder(@PathVariable("orderId") String orderId){
        denyOrderService.deny(orderId);
        return ResponseEntity.noContent().build();
    }
}
