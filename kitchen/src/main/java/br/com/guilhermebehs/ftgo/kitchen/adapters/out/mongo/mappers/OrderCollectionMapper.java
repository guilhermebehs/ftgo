package br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.mappers;


import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.AddressCollection;
import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.OrderCollection;
import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.OrderItemCollection;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Address;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Order;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.OrderItem;

import java.util.stream.Collectors;

public class OrderCollectionMapper {

    public static Order toOrder(OrderCollection orderCollection){


        var address = new Address(
                orderCollection.getCustomerAddress().getDescription(),
                orderCollection.getCustomerAddress().getNeighbourhood(),
                orderCollection.getCustomerAddress().getComplement(),
                orderCollection.getCustomerAddress().getCity(),
                orderCollection.getCustomerAddress().getState(),
                orderCollection.getCustomerAddress().getCountry()
        );

        var items = orderCollection.getItems()
                .stream().map(orderItem -> new OrderItem(
                        orderItem.getDescription(),
                        orderItem.getAmount()
                ))
                .collect(Collectors.toList());

        return new Order(
                orderCollection.getOrderId(),
                address,
                orderCollection.getOrderStatus(),
                orderCollection.getDeliveryDate(),
                orderCollection.getKitchen(),
                items
        );
    }

    public static OrderCollection fromOrder(Order order){

        var address = new AddressCollection(
                order.getCustomerAddress().getDescription(),
                order.getCustomerAddress().getNeighbourhood(),
                order.getCustomerAddress().getComplement(),
                order.getCustomerAddress().getCity(),
                order.getCustomerAddress().getState(),
                order.getCustomerAddress().getCountry()
        );

        var items = order.getItems()
                .stream().map(orderItem -> new OrderItemCollection(
                        orderItem.getDescription(),
                        orderItem.getAmount()
                ))
                .collect(Collectors.toList());

        return new OrderCollection(
                order.getOrderId(),
                address,
                order.getDeliveryDate(),
                order.getOrderStatus(),
                order.getKitchen(),
                items
        );
    }
}
