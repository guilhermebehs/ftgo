package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderDeliveredEvent;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderDeliveredNotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class DeliverOrderService {

    private final OrderRepository orderRepository;
    private final OrderDeliveredNotificationService orderDeliveredNotificationService;

    public DeliverOrderService(OrderRepository orderRepository,
                               OrderDeliveredNotificationService orderDeliveredNotificationService) {
        this.orderRepository = orderRepository;
        this.orderDeliveredNotificationService = orderDeliveredNotificationService;
    }

    public void deliver(String orderId){

        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException());

        order.delivered();
        orderRepository.save(order);

        orderDeliveredNotificationService.notify(new OrderDeliveredEvent(orderId, LocalDateTime.now()));

    }
}
