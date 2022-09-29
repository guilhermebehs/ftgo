package br.com.guilhermebehs.kitchen.domain.services;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.kitchen.domain.ports.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelBookedProductAmountService {

    private final ProductRepository productRepository;

    public CancelBookedProductAmountService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void cancel(ProductId productId, Integer amount){


        var product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException());

        product.cancelAmountDecrease(amount);

        productRepository.save(product);

    }
}
