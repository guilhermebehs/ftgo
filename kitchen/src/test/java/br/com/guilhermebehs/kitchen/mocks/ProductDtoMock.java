package br.com.guilhermebehs.kitchen.mocks;

import br.com.guilhermebehs.kitchen.domain.dtos.CreateProductDto;

public class ProductDtoMock {

    public static CreateProductDto mock(){
        return new CreateProductDto("Some product", "Some kitchen", true, 10);
    }

    public static CreateProductDto mockWithNullDescription(){
        return new CreateProductDto(null, "Some kitchen", true, 10);
    }

    public static CreateProductDto mockWithBlankDescription(){
        return new CreateProductDto("", "Some kitchen", true, 10);
    }

    public static CreateProductDto mockWithNullKitchen(){
        return new CreateProductDto("Some product", null, true, 10);
    }

    public static CreateProductDto mockWithBlankKitchen(){
        return new CreateProductDto("Some product", "", true, 10);
    }

    public static CreateProductDto mockWithNullIsActive(){
        return new CreateProductDto("Some product", "Some kitchen",null, 10);
    }

    public static CreateProductDto mockWithNullAvailableAmount(){
        return new CreateProductDto("Some product", "Some kitchen",true, null);
    }

    public static CreateProductDto mockWithNegativeAvailableAmount(){
        return new CreateProductDto("Some product", "Some kitchen",true, -1);
    }

}
