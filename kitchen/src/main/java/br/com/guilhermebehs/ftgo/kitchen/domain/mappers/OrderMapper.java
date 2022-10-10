package br.com.guilhermebehs.ftgo.kitchen.domain.mappers;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Address;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Order;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.dtos.OrderDetailsDto;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.enums.OrderStatus;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order map(OrderDetailsDto orderDetailsDto){
        var address = new Address(
                orderDetailsDto.getCustomerAddress().getDescription(),
                orderDetailsDto.getCustomerAddress().getNeighbourhood(),
                orderDetailsDto.getCustomerAddress().getComplement(),
                orderDetailsDto.getCustomerAddress().getCity(),
                orderDetailsDto.getCustomerAddress().getState(),
                orderDetailsDto.getCustomerAddress().getCountry());

        var items = orderDetailsDto.getItems()
                .stream()
                .map((orderItemDto)-> new OrderItem(orderItemDto.getDescription(), orderItemDto.getAmount()))
                .collect(Collectors.toList());

        return new Order(
                orderDetailsDto.getOrderId(),
                address,
                OrderStatus.PENDING_ACCEPTANCE,
                null,
                orderDetailsDto.getKitchen(),
                items);
    }

}
