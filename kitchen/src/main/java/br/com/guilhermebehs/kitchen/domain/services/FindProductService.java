package br.com.guilhermebehs.kitchen.domain.services;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.entities.Product;
import br.com.guilhermebehs.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.kitchen.domain.ports.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class FindProductService {

    private final ProductRepository productRepository;

    public FindProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(ProductId productId){

        return productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException());
    }
}
