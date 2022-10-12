package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.ProductRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderDeniedNotificationService;
import br.com.guilhermebehs.ftgo.kitchen.mocks.OrderMock;
import br.com.guilhermebehs.ftgo.kitchen.mocks.ProductMock;
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
import static org.mockito.Mockito.*;

@SpringBootTest(classes = DenyOrderService.class)
class DenyOrderServiceTest {

    @Autowired
    DenyOrderService denyOrderService;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderDeniedNotificationService orderDeniedNotificationService;

    @BeforeEach
    void setUp() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderMock.mock()));
        when(productRepository.findById(any())).thenReturn(Optional.of(ProductMock.mock()));
    }


    @DisplayName("should deny order correctly")
    @Test
    public void shouldDenyOrderCorrectly(){
        assertDoesNotThrow(()-> denyOrderService.deny("some id"));
        verify(productRepository,times(1)).save(any());
    }

    @DisplayName("should throw when order is not found")
    @Test
    public void shouldThrowWhenOrderIsNotFound(){
        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        var exception = assertThrows(OrderNotFoundException.class,
                ()-> denyOrderService.deny("some id"));

        assertThat(exception.getMessage()).isEqualTo("order not found");
        verify(productRepository,times(0)).save(any());
    }
}