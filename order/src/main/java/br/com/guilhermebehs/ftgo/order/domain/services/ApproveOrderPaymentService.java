package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ApproveOrderPaymentService {

    private final OrderRepository orderRepository;
    private final KitchenService kitchenService;

    public ApproveOrderPaymentService(OrderRepository orderRepository, KitchenService kitchenService) {
        this.orderRepository = orderRepository;
        this.kitchenService = kitchenService;
    }


    public void approve(OrderPaymentApprovedEvent orderPaymentApprovedEvent){
        var order = orderRepository.findById(orderPaymentApprovedEvent.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException("order "+orderPaymentApprovedEvent.getOrderId()+" not found"));

        order.preparing();
        orderRepository.save(order);
        for(OrderItem orderItem: order.getItems())
           kitchenService.confirmBookedProductAmount(orderItem.getDescription(), order.getKitchen(), orderItem.getAmount());

    }
}
