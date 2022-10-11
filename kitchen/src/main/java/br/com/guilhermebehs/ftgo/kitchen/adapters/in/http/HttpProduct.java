package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http;

import br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.dtos.FindProductDto;
import br.com.guilhermebehs.ftgo.kitchen.domain.dtos.CreateProductDto;
import br.com.guilhermebehs.ftgo.kitchen.domain.services.*;
import br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.mappers.FindProductDtoMapper;
import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class HttpProduct {


    private final CreateProductService createProductService;
    private final ConfirmBookedProductAmountService confirmBookedProductAmountService;
    private final CancelBookedProductAmountService cancelBookedProductAmountService;
    private final BookProductAmountService bookProductAmountService;
    private final FindProductService findProductService;

    public HttpProduct(CreateProductService createProductService,
                       ConfirmBookedProductAmountService confirmBookedProductAmountService,
                       CancelBookedProductAmountService cancelBookedProductAmountService,
                       BookProductAmountService bookProductAmountService,
                       FindProductService findProductService) {
        this.createProductService = createProductService;
        this.confirmBookedProductAmountService = confirmBookedProductAmountService;
        this.cancelBookedProductAmountService = cancelBookedProductAmountService;
        this.bookProductAmountService = bookProductAmountService;
        this.findProductService = findProductService;
    }


    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductDto createProductDto){
        createProductService.create(createProductDto);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{productDescription}/kitchens/{kitchenDescription}/amount/{amount}/book-amount")
    public ResponseEntity<Void> bookProductAmount(@PathVariable("productDescription") String productDescription,
                                                  @PathVariable("kitchenDescription") String kitchenDescription,
                                                  @PathVariable("amount") Integer amount){
        bookProductAmountService.book(new ProductId(productDescription, kitchenDescription), amount);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productDescription}/kitchens/{kitchenDescription}/amount/{amount}/confirm-booked-amount")
    public ResponseEntity<Void> confirmBookedProductAmount(@PathVariable("productDescription") String productDescription,
                                                  @PathVariable("kitchenDescription") String kitchenDescription,
                                                  @PathVariable("amount") Integer amount){
        confirmBookedProductAmountService.confirm(new ProductId(productDescription, kitchenDescription), amount);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productDescription}/kitchens/{kitchenDescription}/amount/{amount}/cancel-booked-amount")
    public ResponseEntity<Void> cancelBookedProductAmount(@PathVariable("productDescription") String productDescription,
                                                           @PathVariable("kitchenDescription") String kitchenDescription,
                                                           @PathVariable("amount") Integer amount){
        cancelBookedProductAmountService.cancel(new ProductId(productDescription, kitchenDescription), amount);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productDescription}/kitchens/{kitchenDescription}")
    public ResponseEntity<FindProductDto> findProductById(@PathVariable("productDescription") String productDescription,
                                                          @PathVariable("kitchenDescription") String kitchenDescription){

        var product = findProductService.findById(new ProductId(productDescription, kitchenDescription));
        return ResponseEntity.ok(FindProductDtoMapper.fromProduct(product));
    }
}
