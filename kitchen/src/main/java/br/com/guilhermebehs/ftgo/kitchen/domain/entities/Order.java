package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {

    private String orderId;
    private Address customerAddress;
    private OrderStatus orderStatus;
    private LocalDateTime deliveryDate;
    private String kitchen;
    private List<OrderItem> items;


    public void startPreparing(){
        if(!orderStatus.equals(OrderStatus.PENDING_ACCEPTANCE))
            throw new IllegalStateException("order status not allowed in this step");
        orderStatus = OrderStatus.PREPARING;
    }

    public void deny(){
        if(!orderStatus.equals(OrderStatus.PENDING_ACCEPTANCE))
        throw new IllegalStateException("order status not allowed in this step");
        orderStatus = OrderStatus.DENIED;
    }


    public void delivered(){
        if(!orderStatus.equals(OrderStatus.PREPARING))
            throw new IllegalStateException("order status not allowed in this step");
        deliveryDate = LocalDateTime.now();
        orderStatus = OrderStatus.DELIVERED;
    }

}
