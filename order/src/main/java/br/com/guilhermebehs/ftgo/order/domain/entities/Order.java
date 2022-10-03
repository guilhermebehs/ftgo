package br.com.guilhermebehs.ftgo.order.domain.entities;

import br.com.guilhermebehs.ftgo.order.domain.entities.enums.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Getter
public class Order {

    private String orderId;
    private String customerName;
    private Address customerAddress;
    private LocalDateTime orderCreatedOn;
    private LocalDateTime deliveryDateForecast;
    private LocalDateTime deliveryDate;
    private OrderStatus orderStatus;
    private String kitchen;
    private List<OrderItem> items;
    private String creditCard;



    public Order(String orderId, String customerName, Address customerAddress, LocalDateTime orderCreatedOn,
                 LocalDateTime deliveryDateForecast, String kitchen, List<OrderItem> items, String creditCard)
    {

        if(deliveryDateForecast.isBefore(orderCreatedOn))
            throw new IllegalArgumentException("delivery_date_forecast cannot be before order_created_on");

        this.orderId = orderId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderCreatedOn = orderCreatedOn;
        this.deliveryDateForecast = deliveryDateForecast;
        this.orderStatus = OrderStatus.PAYMENT_PENDING;
        this.kitchen = kitchen;
        this.items = items;
        this.creditCard = creditCard;
    }


    public Double calculateTotalPrice(){
        return items.stream()
                .map(item -> item.getPrice() * item.getAmount())
                .reduce(0D, (total, price)-> total + price);
    }

    public void approvePayment(){
        if(!orderStatus.equals(OrderStatus.PAYMENT_PENDING))
            throw new IllegalStateException("order status not allowed in this step");
        orderStatus = OrderStatus.PREPARING;
    }

    public void denyPayment(){
        if(!orderStatus.equals(OrderStatus.PAYMENT_PENDING))
            throw new IllegalStateException("order status not allowed in this step");
        orderStatus = OrderStatus.PAYMENT_DENIED;
    }

    public void delivered(){
        if(!orderStatus.equals(OrderStatus.ON_THE_WAY))
            throw new IllegalStateException("order status not allowed in this step");
        deliveryDate = LocalDateTime.now();
        orderStatus = OrderStatus.DELIVERED;
    }


    public static String generateId(){
        return String.valueOf(System.currentTimeMillis() + (long)new Random().nextInt(1000));
    }

}
