package br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemCollection {

    private String description;
    private Integer amount;
}
