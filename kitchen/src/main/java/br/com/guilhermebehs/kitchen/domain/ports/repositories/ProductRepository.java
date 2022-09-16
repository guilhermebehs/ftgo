package br.com.guilhermebehs.kitchen.domain.ports.repositories;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.entities.Product;

import java.util.Optional;

public interface ProductRepository {

     Optional<Product> findById(ProductId productId);
     Product save(Product product);
}
