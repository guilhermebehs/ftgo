package br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "order")
@Getter
@Setter
@AllArgsConstructor
public class OrderCollection {

    @Id
    @Field("order_id")
    private String orderId;

    @Field("customer_address")
    private AddressCollection customerAddress;

    @Field("delivery_date")
    private LocalDateTime deliveryDate;

    @Field("order_status")
    private OrderStatus orderStatus;

    private String kitchen;

    private List<OrderItemCollection> items;

}
