package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCollectionId {

    private String product;

    private String kitchen;


    @Override
    public String toString() {
        return "ProductCollectionId{" +
                "product='" + product + '\'' +
                ", kitchen='" + kitchen + '\'' +
                '}';
    }
}
