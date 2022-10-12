package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderConfirmedEvent;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderConfirmedNotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfirmOrderService {

    private final OrderRepository orderRepository;
    private final OrderConfirmedNotificationService orderConfirmedNotificationService;

    public ConfirmOrderService(OrderRepository orderRepository,
                               OrderConfirmedNotificationService orderConfirmedNotificationService) {
        this.orderRepository = orderRepository;
        this.orderConfirmedNotificationService = orderConfirmedNotificationService;
    }

    public void confirm(String orderId){
        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException());

        order.startPreparing();

        orderRepository.save(order);

        orderConfirmedNotificationService.notify(new OrderConfirmedEvent(orderId, LocalDateTime.now()));
    }

}
