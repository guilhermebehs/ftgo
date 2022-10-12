package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfirmOrderService {

    private final OrderRepository orderRepository;

    public ConfirmOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void confirm(String orderId){
        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("order "+orderId+" not found"));

        order.confirm();
        orderRepository.save(order);
    }
}
