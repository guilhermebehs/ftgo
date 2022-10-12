package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.ProductId;
import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderDeniedEvent;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.ProductRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderDeniedNotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DenyOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDeniedNotificationService orderDeniedNotificationService;

    public DenyOrderService(OrderRepository orderRepository,
                            ProductRepository productRepository, OrderDeniedNotificationService orderDeniedNotificationService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDeniedNotificationService = orderDeniedNotificationService;
    }

    public void deny(String orderId){
        var order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException());

        order.deny();

        orderRepository.save(order);

        order.getItems().forEach((orderItem)->{

            var productId = new ProductId(orderItem.getDescription(), order.getKitchen());
            var product = productRepository.findById(productId)
                    .orElseThrow(()-> new ProductNotFoundException());
            product.increaseAvailableAmount(orderItem.getAmount());
            productRepository.save(product);
        });


        orderDeniedNotificationService.notify(new OrderDeniedEvent(orderId, LocalDateTime.now()));
    }

}
