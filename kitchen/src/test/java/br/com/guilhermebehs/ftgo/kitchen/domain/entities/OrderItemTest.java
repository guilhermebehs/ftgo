package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("OrderItem")
class OrderItemTest {


    @Test
    @DisplayName("should change amount correctly")
    public void shouldChangeAmountCorrectly(){

        assertDoesNotThrow(()->{
           var item = new OrderItem("some description", 1);
           item.changeAmount(3);
           assertThat(item.getAmount()).isEqualTo(3);
        });

    }


    @Test
    @DisplayName("should throw when changing amount to 0")
    public void shouldThrowWhenChangingAmountTo0(){

        var exception = assertThrows(IllegalStateException.class,()->{
            var item = new OrderItem("some description", 1);
            item.changeAmount(0);
        });

        assertThat(exception.getMessage()).isEqualTo("item amount must be equal or greater than 1");

    }


    @Test
    @DisplayName("should throw when changing amount to a negative value")
    public void shouldThrowWhenChangingAmountToANegativeValue(){

        var exception = assertThrows(IllegalStateException.class,()->{
            var item = new OrderItem("some description",  1);
            item.changeAmount(-1);
        });

        assertThat(exception.getMessage()).isEqualTo("item amount must be equal or greater than 1");

    }

}