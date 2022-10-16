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

@SpringBootTest(classes = DeliverOrderService.class)
class DeliverOrderServiceTest {

    @Autowired
    DeliverOrderService deliverOrderService;

    @MockBean
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        var order = OrderMock.mock();
        order.approvePayment();
        order.confirm();
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
    }

    @Test
    @DisplayName("should deliver order correctly")
    public void shouldDeliverOrderCorrectly(){
        assertDoesNotThrow(()-> deliverOrderService.deliver("some order"));
    }

    @Test
    @DisplayName("should throw when order is not found")
    public void shouldThrowWhenOrderIsNotFound(){

        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        var exception = assertThrows(OrderNotFoundException.class,
                ()-> deliverOrderService.deliver("some order"));

        assertThat(exception.getMessage()).isEqualTo("order some order not found");
    }
}