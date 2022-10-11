package br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;
import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.ProductId;

import java.util.Optional;

public interface ProductRepository {

     Optional<Product> findById(ProductId productId);
     Product save(Product product);
}
