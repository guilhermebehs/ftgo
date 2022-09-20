package br.com.guilhermebehs.kitchen.adapters.in.http;

import br.com.guilhermebehs.kitchen.adapters.in.http.dtos.FindProductDto;
import br.com.guilhermebehs.kitchen.adapters.in.http.mappers.FindProductDtoMapper;
import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.domain.dtos.CreateProductDto;
import br.com.guilhermebehs.kitchen.domain.services.CreateProductService;
import br.com.guilhermebehs.kitchen.domain.services.FindProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class HttpProduct {


    private final CreateProductService createProductService;
    private final FindProductService findProductService;

    public HttpProduct(CreateProductService createProductService, FindProductService findProductService) {
        this.createProductService = createProductService;
        this.findProductService = findProductService;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductDto createProductDto){
        createProductService.create(createProductDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productDescription}/kitchens/{kitchenDescription}")
    public ResponseEntity<FindProductDto> findProductById(@PathVariable("productDescription") String productDescription,
                                                          @PathVariable("kitchenDescription") String kitchenDescription){

        var product = findProductService.findById(new ProductId(productDescription, kitchenDescription));
        return ResponseEntity.ok(FindProductDtoMapper.fromProduct(product));
    }
}
