package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.FindProductDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.InvalidItemException;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import br.com.guilhermebehs.ftgo.order.domain.ports.ValidateOrderPaymentNotificationService;
import br.com.guilhermebehs.ftgo.order.mocks.CreateOrderDtoMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CreateOrderService.class)
class CreateOrderServiceTest {

    @Autowired
    CreateOrderService createOrderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    ValidateOrderPaymentNotificationService validateOrderPaymentNotificationService;

    @MockBean
    KitchenService kitchenService;

    @BeforeEach
    void setUp() {

        var findProductDtoOpt = Optional.of(
                new FindProductDto(
                "some product",
                "some kitchen",
                true,
                10,
                0
         ));

        when(kitchenService.getProductByNameAndKitchen(any(), any())).thenReturn(findProductDtoOpt);

    }


    @Test
    @DisplayName("should create order correctly")
    public void shouldCreateOrderCorrectly(){
        var createOrderDto = CreateOrderDtoMock.mock();

        var orderId = assertDoesNotThrow(()-> createOrderService.create(createOrderDto));

        assertNotNull(orderId);

    }


    @Test
    @DisplayName("should throw when some item is not found")
    public void shouldThrowWhenSomeItemIsNotFound(){
        var createOrderDto = CreateOrderDtoMock.mock();

        when(kitchenService.getProductByNameAndKitchen(any(), any())).thenReturn(Optional.empty());


        var exception = assertThrows(InvalidItemException.class,
                ()-> createOrderService.create(createOrderDto));

        assertThat(exception.getMessage()).isEqualTo("Product '"+createOrderDto.getItems().get(0).getDescription()+"' not found");

    }


    @Test
    @DisplayName("should throw when some item has no amount enough")
    public void shouldThrowWhenSomeItemHasNoAmountEnough(){
        var createOrderDto = CreateOrderDtoMock.mock();

        var findProductDtoOpt = Optional.of(
                new FindProductDto(
                        "some product",
                        "some kitchen",
                        true,
                        0,
                        0
                ));

        when(kitchenService.getProductByNameAndKitchen(any(), any())).thenReturn(findProductDtoOpt);


        var exception = assertThrows(InvalidItemException.class,
                ()-> createOrderService.create(createOrderDto));

        assertThat(exception.getMessage()).isEqualTo("Product '"+createOrderDto.getItems().get(0).getDescription()+"' not available");

    }


    @Test
    @DisplayName("should throw when some item is not active")
    public void shouldThrowWhenSomeItemIsNotActive(){
        var createOrderDto = CreateOrderDtoMock.mock();

        var findProductDtoOpt = Optional.of(
                new FindProductDto(
                        "some product",
                        "some kitchen",
                        false,
                        10,
                        0
                ));

        when(kitchenService.getProductByNameAndKitchen(any(), any())).thenReturn(findProductDtoOpt);


        var exception = assertThrows(InvalidItemException.class,
                ()-> createOrderService.create(createOrderDto));

        assertThat(exception.getMessage()).isEqualTo("Product '"+createOrderDto.getItems().get(0).getDescription()+"' not available");

    }
}