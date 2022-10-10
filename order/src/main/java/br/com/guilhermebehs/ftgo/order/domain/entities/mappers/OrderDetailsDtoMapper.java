package br.com.guilhermebehs.ftgo.order.domain.entities.mappers;

import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.AddressDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderDetailsDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderItemDto;

import java.util.stream.Collectors;

public class OrderDetailsDtoMapper {

    public static OrderDetailsDto map(Order order){

        var addressDto = new AddressDto(
                order.getCustomerAddress().getDescription(),
                order.getCustomerAddress().getNeighbourhood(),
                order.getCustomerAddress().getComplement(),
                order.getCustomerAddress().getCity(),
                order.getCustomerAddress().getState(),
                order.getCustomerAddress().getCountry());

        var items = order.getItems()
                .stream()
                .map(item -> new OrderItemDto(item.getDescription(), item.getPrice(), item.getAmount()))
                .collect(Collectors.toList());

        return new OrderDetailsDto(
                order.getOrderId(),
                addressDto,
                items,
                order.getKitchen()
        );

    }

}
