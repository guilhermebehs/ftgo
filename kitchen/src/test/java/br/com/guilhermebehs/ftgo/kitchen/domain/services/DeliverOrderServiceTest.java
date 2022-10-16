package br.com.guilhermebehs.ftgo.kitchen.domain.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.OrderNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderDeliveredNotificationService;
import br.com.guilhermebehs.ftgo.kitchen.mocks.OrderMock;
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

@SpringBootTest(classes = DeliverOrderService.class)
class DeliverOrderServiceTest {

    @Autowired
    DeliverOrderService deliverOrderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderDeliveredNotificationService orderDeliveredNotificationService;

    @BeforeEach
    void setUp() {
        var order = OrderMock.mock();
        order.startPreparing();
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
    }


    @DisplayName("should deliver order correctly")
    @Test
    public void shouldDeliverOrderCorrectly(){
        assertDoesNotThrow(()-> deliverOrderService.deliver("some id"));
    }

    @DisplayName("should throw when order is not found")
    @Test
    public void shouldThrowWhenOrderIsNotFound(){
        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        var exception = assertThrows(OrderNotFoundException.class,
                ()-> deliverOrderService.deliver("some id"));

        assertThat(exception.getMessage()).isEqualTo("order not found");
    }
}