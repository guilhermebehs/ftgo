package br.com.guilhermebehs.ftgo.order.domain.entities;

import br.com.guilhermebehs.ftgo.order.domain.entities.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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

            var items = List.of(new OrderItem("some item", 10D, 1));


            new Order(
                    "some order id",
                    "some customer name",
                    address,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    "some kitchen",
                    items,
                    "123456"
                    );
        });
    }


    @Test
    @DisplayName("should throw when deliveryDateForecast is before orderCreatedOn")
    public void shouldThrowWhenDeliveryDateForecastIsBeforeOrderCreatedOn(){

       var exception = assertThrows(IllegalArgumentException.class,()->{

            var address = new Address(
                    "some street",
                    "some neighbourhood",
                    "some complement",
                    "some city",
                    "some state",
                    "some country"
            );

            var items = List.of(new OrderItem("some item", 10D, 1));


            new Order(
                    "some order id",
                    "some customer name",
                    address,
                    LocalDateTime.now(),
                    LocalDateTime.now().minusSeconds(1),
                    "some kitchen",
                    items,
                    "123456"
            );
        });

       assertThat(exception.getMessage()).isEqualTo("delivery_date_forecast cannot be before order_created_on");
    }


    @Test
    @DisplayName("should calculate total price correctly")
    public void shouldCalculateTotalPriceCorrectly(){


            var address = new Address(
                    "some street",
                    "some neighbourhood",
                    "some complement",
                    "some city",
                    "some state",
                    "some country"
            );

            var items = List.of(
                    new OrderItem("some item", 10D, 1),
                    new OrderItem("another item", 15D, 2));


           var order = new Order(
                    "some order id",
                    "some customer name",
                    address,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                   "some kitchen",
                    items,
                   "123456"
            );

           assertThat(order.calculateTotalPrice()).isEqualTo(40D);

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

            var items = List.of(new OrderItem("some item", 10D, 1));


            var order = new Order(
                    "some order id",
                    "some customer name",
                    address,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    "some kitchen",
                    items,
                    "123456"
            );
            changeOrderStatus(order, OrderStatus.CANCELED);
            order.approvePayment();
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

            var items = List.of(new OrderItem("some item", 10D, 1));


            var order = new Order(
                    "some order id",
                    "some customer name",
                    address,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    "some kitchen",
                    items,
                    "123456"
            );
            changeOrderStatus(order, OrderStatus.CANCELED);
            order.denyPayment();
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

            var items = List.of(new OrderItem("some item", 10D, 1));


            var order = new Order(
                    "some order id",
                    "some customer name",
                    address,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    "some kitchen",
                    items,
                    "123456"
            );
            changeOrderStatus(order, OrderStatus.CANCELED);
            order.delivered();
        });

        assertThat(exception.getMessage()).isEqualTo("order status not allowed in this step");
    }
}