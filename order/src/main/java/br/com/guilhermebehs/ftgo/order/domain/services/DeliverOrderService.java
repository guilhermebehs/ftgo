package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliverOrderService {

    private final OrderRepository orderRepository;

    public DeliverOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void deliver(String orderId){
        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("order "+orderId+" not found"));

        order.delivered();
        orderRepository.save(order);
    }
}
