package br.com.guilhermebehs.kitchen.domain.services;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.dtos.ProductDto;
import br.com.guilhermebehs.kitchen.domain.entities.Product;
import br.com.guilhermebehs.kitchen.domain.ports.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductDto productDto){

       var productId = new ProductId(productDto.getDescription(), productDto.getKitchen());

       if(productRepository.findById(productId).isPresent())
           throw new IllegalArgumentException("product already exists");

       var newProduct = new Product(productId, productDto.isActive(), productDto.getAvailableAmount(), 0);

       return productRepository.save(newProduct);

    }
}
