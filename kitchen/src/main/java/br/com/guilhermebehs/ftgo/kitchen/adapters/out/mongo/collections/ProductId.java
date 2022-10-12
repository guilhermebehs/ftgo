package br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ProductId {

    private String product;
    private String kitchen;
}
