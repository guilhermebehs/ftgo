package br.com.guilhermebehs.kitchen.domain.services;

import br.com.guilhermebehs.kitchen.domain.entities.Product;
import br.com.guilhermebehs.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.kitchen.domain.ports.repositories.ProductRepository;
import br.com.guilhermebehs.kitchen.mocks.ProductMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FindProductService.class)
@DisplayName("FindProductService")
class FindProductServiceTest {

    @Autowired
    private FindProductService findProductService;

    @MockBean
    private ProductRepository productRepository;

    private Product productMock;

    @BeforeEach
    public void setUp() {

        productMock = ProductMock.mock();
        when(productRepository.findById(any())).thenReturn(Optional.of(productMock));
    }


    @Test
    @DisplayName("should find product")
    public void shouldFindProduct(){
        var productId = ProductMock.mock().getProductId();
        var productFound = assertDoesNotThrow(()-> findProductService.findById(productId));

        assertThat(productMock).isEqualTo(productFound);
    }

    @Test
    @DisplayName("should throw when product is not found")
    public void shouldThrowWhenProductIsNotFound(){

        when(productRepository.findById(any())).thenReturn(Optional.empty());

        var productId = ProductMock.mock().getProductId();

       var exception = assertThrows(ProductNotFoundException.class,
               ()-> findProductService.findById(productId));

        assertThat(exception.getMessage()).isEqualTo("product not found");
    }
}