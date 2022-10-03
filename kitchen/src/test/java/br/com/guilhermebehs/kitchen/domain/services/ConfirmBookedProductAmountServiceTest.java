package br.com.guilhermebehs.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.ProductRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.services.ConfirmBookedProductAmountService;
import br.com.guilhermebehs.kitchen.mocks.ProductMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ConfirmBookedProductAmountService.class)
@DisplayName("ConfirmBookedProductAmountService")
class ConfirmBookedProductAmountServiceTest {

    @Autowired
    private ConfirmBookedProductAmountService confirmBookedProductAmountService;

    @MockBean
    private ProductRepository productRepository;

    private Product productMock;

    @BeforeEach
    void setUp() {

        productMock = ProductMock.mockWithBookedAmount();

        when(productRepository.findById(any())).thenReturn(Optional.of(productMock));
    }



    @Test
    @DisplayName("should confirm booked amount correctly")
    public void shouldConfirmBookedAmountCorrectly(){

        var productId = new ProductId("some product", "some kitchen");
        assertDoesNotThrow(()-> confirmBookedProductAmountService.confirm(productId, 1));

    }

    @Test
    @DisplayName("should throw when products does not exist")
    public void shouldThrowWhenProductDoesNotExist(){

        when(productRepository.findById(any())).thenReturn(Optional.empty());

        var productId = new ProductId("some product", "some kitchen");
        assertThrows(ProductNotFoundException.class,
                ()-> confirmBookedProductAmountService.confirm(productId, 1));
    }
}