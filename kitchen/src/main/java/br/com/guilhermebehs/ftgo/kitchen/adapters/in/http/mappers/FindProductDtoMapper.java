package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.mappers;

import br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.dtos.FindProductDto;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;

public class FindProductDtoMapper {

    public static FindProductDto fromProduct(Product product){
        return new FindProductDto(
                product.getProductId().getProduct(),
                product.getProductId().getKitchen(),
                product.isActive(),
                product.getAvailableAmount(),
                product.getBookedAmount()
                );
    }
}
