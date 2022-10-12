package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.ftgo.kitchen.domain.mappers.OrderMapper;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderService;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public CreateOrderService(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    public void create(OrderPaymentApprovedEvent orderPaymentApprovedEvent){
        var orderDetails = orderService.getOrderDetails(orderPaymentApprovedEvent.getOrderId());
        var order = OrderMapper.map(orderDetails);
        orderRepository.save(order);
    }


}
