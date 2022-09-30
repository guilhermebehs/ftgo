package br.com.guilhermebehs.ftgo.order.adapters.out.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemCollection {

    private String description;
    private Double price;
    private Integer amount;
}
