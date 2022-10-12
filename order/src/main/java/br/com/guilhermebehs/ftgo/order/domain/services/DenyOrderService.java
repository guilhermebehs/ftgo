package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DenyOrderService {

    private final OrderRepository orderRepository;

    public DenyOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void deny(String orderId){
        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("order "+orderId+" not found"));

        order.denyByKitchen();
        orderRepository.save(order);
    }
}
