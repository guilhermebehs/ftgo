package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.events.OrderPaymentDeniedEvent;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DenyOrderPaymentService {

    private final OrderRepository orderRepository;
    private final KitchenService kitchenService;

    public DenyOrderPaymentService(OrderRepository orderRepository, KitchenService kitchenService) {
        this.orderRepository = orderRepository;
        this.kitchenService = kitchenService;
    }


    public void deny(OrderPaymentDeniedEvent orderPaymentDeniedEvent){
        var order = orderRepository.findById(orderPaymentDeniedEvent.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException("order "+orderPaymentDeniedEvent.getOrderId()+" not found"));

        order.denyPayment();
        orderRepository.save(order);
        for(OrderItem orderItem: order.getItems())
           kitchenService.cancelBookedProductAmount(orderItem.getDescription(), order.getKitchen(), orderItem.getAmount());

    }
}
