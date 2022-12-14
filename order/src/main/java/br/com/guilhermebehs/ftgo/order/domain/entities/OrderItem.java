package br.com.guilhermebehs.ftgo.order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class OrderItem {

    private String description;
    private Double price;
    private Integer amount;

    public void changeAmount(Integer amount){
        if(amount == null || amount.intValue() < 1)
            throw new IllegalStateException("item amount must be equal or greater than 1");

        this.amount = amount;
    }
}
