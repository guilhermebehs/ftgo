package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.ProductId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Product {

    private ProductId productId;

    private boolean isActive;

    private int availableAmount;

    private int bookedAmount;

    public void bookAmount(int amount){
        if(!isActive)
            throw new IllegalStateException("product is inactive");
        if(availableAmount < amount)
            throw new IllegalStateException("product is unavailable");

        bookedAmount += amount;
        availableAmount -= amount;
    }
    public void concludeAmountDecrease(int amount){
        if(!isActive)
            throw new IllegalStateException("product is inactive");
        if(bookedAmount < amount)
            throw new IllegalStateException("product is unavailable");

        bookedAmount -= amount;
    }

    public void cancelAmountDecrease(int amount){
        if(!isActive)
            throw new IllegalStateException("product is inactive");
        if(bookedAmount < amount)
            throw new IllegalStateException("product is unavailable");

        bookedAmount -= amount;
        availableAmount += amount;
    }

    public void increaseAvailableAmount(int amount){
        if(!isActive)
            throw new IllegalStateException("product is inactive");
        availableAmount += amount;
    }


    public void activate(){
        isActive = true;
    }

    public void deactivate(){
        isActive = false;
    }
}
