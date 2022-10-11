package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Order")
class OrderTest {



    private void changeOrderStatus(Order order, OrderStatus status) throws NoSuchFieldException, IllegalAccessException {

       var orderStatusField = order.getClass().getDeclaredField("orderStatus");
       orderStatusField.setAccessible(true);
       orderStatusField.set(order, status);
    }


    @Test
    @DisplayName("should create order correctly")
    public void shouldCreateOrderCorrectly(){

        assertDoesNotThrow(()->{

            var address = new Address(
                    "some street",
                    "some neighbourhood",
                    "some complement",
                    "some city",
                    "some state",
                    "some country"
            );

            var items = List.of(new OrderItem("some item", 1));


            new Order(
                    "some order id",
                    address,
                    OrderStatus.PENDING_ACCEPTANCE,
                    null,
                    "some kitchen",
                    items
                    );
        });
    }



    @Test
    @DisplayName("should throw when setting status to 'PREPARING' on illegal step")
    public void shouldThrowWhenSettingStatusToPreparingOnIllegalStep(){

        var exception = assertThrows(IllegalStateException.class,()->{

            var address = new Address(
                    "some street",
                    "some neighbourhood",
                    "some complement",
                    "some city",
                    "some state",
                    "some country"
            );

            var items = List.of(new OrderItem("some item", 1));


            var order =  new Order(
                    "some order id",
                    address,
                    OrderStatus.DENIED,
                    null,
                    "some kitchen",
                    items
            );
            order.startPreparing();
        });

        assertThat(exception.getMessage()).isEqualTo("order status not allowed in this step");
    }


    @Test
    @DisplayName("should throw when setting status to 'PAYMENT_DENIED' on illegal step")
    public void shouldThrowWhenSettingStatusToPaymentDeniedOnIllegalStep(){

        var exception = assertThrows(IllegalStateException.class,()->{

                    var address = new Address(
                            "some street",
                            "some neighbourhood",
                            "some complement",
                            "some city",
                            "some state",
                            "some country"
                    );

                    var items = List.of(new OrderItem("some item", 1));


                    var order =  new Order(
                            "some order id",
                            address,
                            OrderStatus.PREPARING,
                            null,
                            "some kitchen",
                            items
                    );

            order.deny();
        });

        assertThat(exception.getMessage()).isEqualTo("order status not allowed in this step");
    }


    @Test
    @DisplayName("should throw when setting status to 'DELIVERED' on illegal step")
    public void shouldThrowWhenSettingStatusToDeliveredOnIllegalStep(){

        var exception = assertThrows(IllegalStateException.class,()->{

            var address = new Address(
                    "some street",
                    "some neighbourhood",
                    "some complement",
                    "some city",
                    "some state",
                    "some country"
            );

            var items = List.of(new OrderItem("some item", 1));


            var order =  new Order(
                    "some order id",
                    address,
                    OrderStatus.DENIED,
                    null,
                    "some kitchen",
                    items
            );

            order.delivered();
        });

        assertThat(exception.getMessage()).isEqualTo("order status not allowed in this step");
    }
}