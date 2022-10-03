package br.com.guilhermebehs.payment.domain.entities;


import br.com.guilhermebehs.ftgo.payment.domain.entities.OrderPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static br.com.guilhermebehs.ftgo.payment.domain.enums.ApprovalStatus.APPROVED;
import static br.com.guilhermebehs.ftgo.payment.domain.enums.ApprovalStatus.DENIED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("OrderPayment")
public class OrderPaymentTest {

    @Test
    @DisplayName("should throw when order id is null")
    public void shouldThrowWhenOrderIdIsNull(){

       var exception =  assertThrows(IllegalArgumentException.class,()->
                        new OrderPayment(
                            null,
                            "5432",
                            20.50,
                            "12",
                              LocalDateTime.now()
                          )
        );

       assertThat(exception.getMessage()).isEqualTo("Invalid order id");
    }


    @Test
    @DisplayName("should throw when order id is empty")
    public void shouldThrowWhenOrderIdIsEmpty(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "",
                        "5432",
                        20.50,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid order id");
    }

    @Test
    @DisplayName("should throw when card number is null")
    public void shouldThrowWhenCardNumberIsNull(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        null,
                        20.50,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid card number");
    }


    @Test
    @DisplayName("should throw when card number is empty")
    public void shouldThrowWhenCardNumberIsEmpty(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "",
                        20.50,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid card number");
    }


    @Test
    @DisplayName("should throw when card number length is greater than 8")
    public void shouldThrowWhenCardNumberIsGreaterThan8(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "123456789",
                        20.50,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid card number");
    }


    @Test
    @DisplayName("should throw when card number length is less than 4")
    public void shouldThrowWhenCardNumberIsLessThan4(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "123",
                        20.50,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid card number");
    }


    @Test
    @DisplayName("should throw when card number has something besides numbers")
    public void shouldThrowWhenCardNumberHasSomethingBesidesNumbers(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "12345A",
                        20.50,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid card number");
    }

    @Test
    @DisplayName("should throw when price is null")
    public void shouldThrowWhenPriceIsNull(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "12345",
                        null,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid price");
    }

    @Test
    @DisplayName("should throw when price is zero")
    public void shouldThrowWhenPriceIsZero(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "12345",
                        0D,
                        "12",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid price");
    }


    @Test
    @DisplayName("should throw when customer id is null")
    public void shouldThrowWhenCustomerIdIsNull(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "12345",
                        20D,
                        null,
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid customer id");
    }

    @Test
    @DisplayName("should throw when customer id is empty")
    public void shouldThrowWhenCustomerIdIsEmpty(){

        var exception =  assertThrows(IllegalArgumentException.class,()->
                new OrderPayment(
                        "12345",
                        "12345",
                        20D,
                        "",
                        LocalDateTime.now()
                )
        );

        assertThat(exception.getMessage()).isEqualTo("Invalid customer id");
    }

    @Test
    @DisplayName("should approve payment correctly")
    public void shouldApprovePaymentCorrectly(){

        var orderPayment =  assertDoesNotThrow(()->
                new OrderPayment(
                        "12345",
                        "12345",
                        20D,
                        "12",
                        LocalDateTime.now()
                )
        );

        orderPayment.approvePayment();
        assertThat(orderPayment.getApprovalStatus()).isEqualTo(APPROVED);
    }

    @Test
    @DisplayName("should deny payment correctly")
    public void shouldDenyPaymentCorrectly(){

        var orderPayment =  assertDoesNotThrow(()->
                new OrderPayment(
                        "12345",
                        "12345",
                        20D,
                        "12",
                        LocalDateTime.now()
                )
        );

        orderPayment.denyPayment();
        assertThat(orderPayment.getApprovalStatus()).isEqualTo(DENIED);
    }


    @Test
    @DisplayName("should change card number correctly")
    public void shouldChangeCardNumberCorrectly(){

        var orderPayment =  assertDoesNotThrow(()->
                new OrderPayment(
                        "12345",
                        "12345",
                        20D,
                        "12",
                        LocalDateTime.now()
                )
        );

        orderPayment.changeCardNumber("54321");
        assertThat(orderPayment.getCardNumber()).isEqualTo("54321");
    }

    @Test
    @DisplayName("should change price correctly")
    public void shouldChangePriceCorrectly(){

        var orderPayment =  assertDoesNotThrow(()->
                new OrderPayment(
                        "12345",
                        "12345",
                        20D,
                        "12",
                        LocalDateTime.now()
                )
        );

        orderPayment.changePrice(10D);
        assertThat(orderPayment.getPrice()).isEqualTo(10D);
    }
}
