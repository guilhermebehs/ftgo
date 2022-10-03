package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.events.OrderPaymentDeniedEvent;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import br.com.guilhermebehs.ftgo.order.mocks.OrderMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DenyOrderPaymentService.class)
class DenyOrderPaymentServiceTest {

    @Autowired
    DenyOrderPaymentService denyOrderPaymentService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    KitchenService kitchenService;

    @BeforeEach
    void setUp() {

        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderMock.mock()));

    }

    @Test
    @DisplayName("should deny order payment correctly")
    public void shouldDenyOrderPaymentCorrectly(){
        assertDoesNotThrow(()->
                denyOrderPaymentService.deny(
                        new OrderPaymentDeniedEvent(
                                "some order id",
                                 LocalDateTime.now())));
    }


    @Test
    @DisplayName("should throw when order is not found")
    public void shouldThrowWhneOrderIsNotFound(){

        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OrderNotFoundException.class, ()->
                denyOrderPaymentService.deny(new OrderPaymentDeniedEvent(
                                            "some order id",
                                            LocalDateTime.now())));


        assertThat(exception.getMessage()).isEqualTo("order some order id not found");
    }
}