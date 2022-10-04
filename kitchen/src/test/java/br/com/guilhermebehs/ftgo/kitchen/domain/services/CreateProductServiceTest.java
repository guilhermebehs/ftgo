package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.ProductRepository;
import br.com.guilhermebehs.ftgo.kitchen.mocks.ProductDtoMock;
import br.com.guilhermebehs.ftgo.kitchen.mocks.ProductMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CreateProductService.class)
@DisplayName("CreateProductService")
class CreateProductServiceTest {

    @Autowired
    private CreateProductService createProductService;

    @MockBean
    private ProductRepository productRepository;

    private Product productMock;

    @BeforeEach
    public void setUp() {

        productMock = ProductMock.mock();

        when(productRepository.findById(any())).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(productMock);
    }


    @Test
    @DisplayName("should create product correctly")
    public void shouldCreateProductCorrectly(){

        var productDtoMock = ProductDtoMock.mock();
        var productCreated = assertDoesNotThrow(()-> createProductService.create(productDtoMock));

        assertThat(productCreated.getProductId().getProduct()).isEqualTo(productDtoMock.getDescription());
        assertThat(productCreated.getProductId().getKitchen()).isEqualTo(productDtoMock.getKitchen());
        assertThat(productCreated.getAvailableAmount()).isEqualTo(productDtoMock.getAvailableAmount());
        assertThat(productCreated.isActive()).isEqualTo(productDtoMock.getIsActive().booleanValue());
        assertThat(productCreated.getBookedAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("should throw when product already exists")
    public void shouldThrowWhenProductAlreadyExists(){

        when(productRepository.findById(any())).thenReturn(Optional.of(productMock));

        var productDtoMock = ProductDtoMock.mock();
        var exception = assertThrows(IllegalArgumentException.class,
                ()-> createProductService.create(productDtoMock));

        assertThat(exception.getMessage()).isEqualTo("product already exists");

    }
}