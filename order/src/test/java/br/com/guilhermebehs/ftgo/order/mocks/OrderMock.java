package br.com.guilhermebehs.ftgo.order.mocks;

import br.com.guilhermebehs.ftgo.order.domain.entities.Address;
import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMock {

    public static Order mock(){

        var address = new Address(
                "some street",
                "some neighbourhood",
                "some complement",
                "some city",
                "some state",
                "some country"
        );

        var items = List.of(new OrderItem("some item", 10D, 1));


        return new Order(
                "some customer name",
                "some customer",
                address,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                "some kitchen",
                items,
                "123456"
        );

    }
}
