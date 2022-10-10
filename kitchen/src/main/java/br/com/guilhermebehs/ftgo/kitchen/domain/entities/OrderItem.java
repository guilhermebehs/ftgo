package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {

    private String description;
    private Integer amount;

    public void changeAmount(Integer amount){
        if(amount == null || amount.intValue() < 1)
            throw new IllegalStateException("item amount must be equal or greater than 1");

        this.amount = amount;
    }
}
