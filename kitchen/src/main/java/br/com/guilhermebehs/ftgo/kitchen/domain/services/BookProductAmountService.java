package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.ProductRepository;
import br.com.guilhermebehs.ftgo.kitchen.adapters.out.collections.ProductId;
import org.springframework.stereotype.Service;

@Service
public class BookProductAmountService {

    private final ProductRepository productRepository;

    public BookProductAmountService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void book(ProductId productId, Integer amount){


        var product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException());

        product.bookAmount(amount);

        productRepository.save(product);

    }
}
