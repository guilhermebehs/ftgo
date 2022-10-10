package br.com.guilhermebehs.ftgo.order.mocks;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.AddressDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderDetailsDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderItemDto;

import java.util.List;

public class OrderDetailsDtoMock {

    public static OrderDetailsDto mock(){

        var address = new AddressDto(
                "some street",
                "some neighbourhood",
                "some complement",
                "some city",
                "some state",
                "some country"
        );

        var items = List.of(new OrderItemDto("some item", 10D, 1));


        return new OrderDetailsDto("some order id", address, items, "some kitchen");
    }
}
