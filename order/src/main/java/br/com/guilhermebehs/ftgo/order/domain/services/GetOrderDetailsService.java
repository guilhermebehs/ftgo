package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderDetailsDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.entities.mappers.OrderDetailsDtoMapper;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class GetOrderDetailsService {

    private final OrderRepository orderRepository;

    public GetOrderDetailsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDetailsDto get(String orderId){

        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("order "+orderId+" not found"));


        return OrderDetailsDtoMapper.map(order);
    }
}
