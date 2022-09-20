package br.com.guilhermebehs.kitchen.mocks;

import br.com.guilhermebehs.kitchen.adapters.in.http.dtos.FindProductDto;

public class FindProductDtoMock {

    public static FindProductDto mock(){
        return new FindProductDto("Some product", "Some kitchen", true, 10, 0);
    }

}
