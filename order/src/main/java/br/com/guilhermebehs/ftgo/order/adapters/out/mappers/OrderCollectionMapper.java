package br.com.guilhermebehs.ftgo.order.adapters.out.mappers;

import br.com.guilhermebehs.ftgo.order.adapters.out.collections.AddressCollection;
import br.com.guilhermebehs.ftgo.order.adapters.out.collections.OrderCollection;
import br.com.guilhermebehs.ftgo.order.adapters.out.collections.OrderItemCollection;
import br.com.guilhermebehs.ftgo.order.domain.entities.Address;
import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;

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
                        orderItem.getPrice(),
                        orderItem.getAmount()
                ))
                .collect(Collectors.toList());

        return new Order(
                orderCollection.getOrderId(),
                orderCollection.getCustomerName(),
                address,
                orderCollection.getOrderCreatedOn(),
                orderCollection.getDeliveryDateForecast(),
                orderCollection.getDeliveryDate(),
                orderCollection.getOrderStatus(),
                orderCollection.getKitchen(),
                items,
                orderCollection.getCreditCard()
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
                        orderItem.getPrice(),
                        orderItem.getAmount()
                ))
                .collect(Collectors.toList());

        return new OrderCollection(
                order.getOrderId(),
                order.getCustomerName(),
                address,
                order.getOrderCreatedOn(),
                order.getDeliveryDateForecast(),
                order.getDeliveryDate(),
                order.getOrderStatus(),
                order.getKitchen(),
                items,
                order.getCreditCard()
        );
    }
}
