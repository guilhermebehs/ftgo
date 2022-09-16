package br.com.guilhermebehs.kitchen.adapters.out.mappers;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductCollection;
import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.entities.Product;
import br.com.guilhermebehs.kitchen.domain.entities.ProductCollectionId;

public class ProductCollectionMapper {

    public static Product toProduct(ProductCollection productCollection){

        var productId = new ProductId(productCollection.getProductId().getProduct() ,
                                      productCollection.getProductId().getKitchen());

        return new Product(
                productId,
                productCollection.isActive(),
                productCollection.getAvailableAmount(),
                productCollection.getBookedAmount()
        );
    }

    public static ProductCollection fromProduct(Product product){

        var productCollectionId = new ProductCollectionId(
                product.getProductId().getProduct(),
                product.getProductId().getKitchen());

        return new ProductCollection(
                productCollectionId,
                product.isActive(),
                product.getAvailableAmount(),
                product.getBookedAmount()
        );
    }
}
