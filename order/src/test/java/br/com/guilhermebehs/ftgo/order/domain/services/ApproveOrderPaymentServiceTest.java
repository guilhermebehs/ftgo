package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.order.domain.events.OrderPaymentApprovedEvent;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ApproveOrderPaymentService.class)
class ApproveOrderPaymentServiceTest {

    @Autowired
    ApproveOrderPaymentService approveOrderPaymentService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    KitchenService kitchenService;

    @BeforeEach
    void setUp() {

        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderMock.mock()));

    }

    @Test
    @DisplayName("should approve order payment correctly")
    public void shouldApproveOrderPaymentCorrectly(){
        assertDoesNotThrow(()->
                approveOrderPaymentService.approve(
                        new OrderPaymentApprovedEvent(
                                "some order id",
                                 LocalDateTime.now())));
    }


    @Test
    @DisplayName("should throw when order is not found")
    public void shouldThrowWhneOrderIsNotFound(){

        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OrderNotFoundException.class, ()->
                approveOrderPaymentService.approve(new OrderPaymentApprovedEvent(
                                            "some order id",
                                            LocalDateTime.now())));


        assertThat(exception.getMessage()).isEqualTo("order some order id not found");
    }
}