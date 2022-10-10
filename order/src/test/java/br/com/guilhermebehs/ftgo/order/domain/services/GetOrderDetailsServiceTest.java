package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import br.com.guilhermebehs.ftgo.order.mocks.OrderMock;
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

@SpringBootTest(classes = GetOrderDetailsService.class)
class GetOrderDetailsServiceTest {

    @Autowired
    GetOrderDetailsService getOrderDetailsService;

    @MockBean
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderMock.mock()));
    }

    @Test
    @DisplayName("should get order details correctly")
    public void shouldgetOrderDetailsCorrectly(){
        assertDoesNotThrow(()-> getOrderDetailsService.get("some order id"));
    }


    @Test
    @DisplayName("should throw when order is not found")
    public void shouldThrowWhneOrderIsNotFound(){

        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OrderNotFoundException.class, ()->
                getOrderDetailsService.get("some order id"));


        assertThat(exception.getMessage()).isEqualTo("order some order id not found");
    }
}