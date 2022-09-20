package br.com.guilhermebehs.kitchen.domain.services;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.dtos.CreateProductDto;
import br.com.guilhermebehs.kitchen.domain.entities.Product;
import br.com.guilhermebehs.kitchen.domain.ports.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(CreateProductDto createProductDto){

       var productId = new ProductId(createProductDto.getDescription(), createProductDto.getKitchen());

       if(productRepository.findById(productId).isPresent())
           throw new IllegalArgumentException("product already exists");

       var newProduct = new Product(productId, createProductDto.getIsActive().booleanValue(), createProductDto.getAvailableAmount(), 0);

       return productRepository.save(newProduct);

    }
}
