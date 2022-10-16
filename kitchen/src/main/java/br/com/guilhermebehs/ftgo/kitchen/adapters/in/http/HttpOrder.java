package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http;

import br.com.guilhermebehs.ftgo.kitchen.domain.services.ConfirmOrderService;
import br.com.guilhermebehs.ftgo.kitchen.domain.services.DeliverOrderService;
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
    private final DeliverOrderService deliverOrderService;

    public HttpOrder(ConfirmOrderService confirmOrderService,
                     DenyOrderService denyOrderService,
                     DeliverOrderService deliverOrderService) {
        this.confirmOrderService = confirmOrderService;
        this.denyOrderService = denyOrderService;
        this.deliverOrderService = deliverOrderService;
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

    @PatchMapping("/{orderId}/deliver")
    public ResponseEntity<Void> deliverOrder(@PathVariable("orderId") String orderId){
        deliverOrderService.
    deliver(orderId);
        return ResponseEntity.noContent().build();
    }
}
