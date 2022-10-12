package br.com.guilhermebehs.ftgo.kitchen.mocks;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.ProductId;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Address;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Order;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.enums.OrderStatus;

import java.util.List;

public class OrderMock {

    public static Order mock(){

        var address = new Address(
                "some description",
                "some neighbourhood",
                null,
                "some city",
                "some state",
                "some country"
                );

        return new Order("order id",
                address,
                OrderStatus.PENDING_ACCEPTANCE,
                null,
                "some kitchen",
                List.of(new OrderItem("some item", 1)));
    }


    public static Product mockWithBookedAmount(){

        var productId = new ProductId("Some product", "Some kitchen");

        return new Product(productId, true, 10, 1);
    }
}
