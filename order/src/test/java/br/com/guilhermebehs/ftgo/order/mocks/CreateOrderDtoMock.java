package br.com.guilhermebehs.ftgo.order.mocks;

import br.com.guilhermebehs.ftgo.order.domain.entities.Address;
import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.CreateOrderDto;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderDtoMock {

    public static CreateOrderDto mock(){

        var address = new Address(
                "some street",
                "some neighbourhood",
                "some complement",
                "some city",
                "some state",
                "some country"
        );

        var items = List.of(new OrderItem("some item", 10D, 1));


        return new CreateOrderDto(
                "some customer name",
                address,
                LocalDateTime.now().plusHours(1),
                items,
                "some kitchen"
        );

    }
}
