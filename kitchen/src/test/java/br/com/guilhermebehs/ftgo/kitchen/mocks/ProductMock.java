package br.com.guilhermebehs.ftgo.kitchen.mocks;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.ProductId;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;

public class ProductMock {

    public static Product mock(){

        var productId = new ProductId("Some product", "Some kitchen");

        return new Product(productId, true, 10, 0);
    }


    public static Product mockWithBookedAmount(){

        var productId = new ProductId("Some product", "Some kitchen");

        return new Product(productId, true, 10, 1);
    }
}
