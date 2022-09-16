package br.com.guilhermebehs.kitchen.mocks;

import br.com.guilhermebehs.kitchen.domain.dtos.ProductDto;

public class ProductDtoMock {

    public static ProductDto mock(){

        return new ProductDto("Some product", "Some kitchen", true, 10);
    }
}
