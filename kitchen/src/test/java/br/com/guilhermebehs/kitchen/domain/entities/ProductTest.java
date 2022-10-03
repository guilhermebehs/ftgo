package br.com.guilhermebehs.kitchen.domain.entities;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Product")
class ProductTest {

    private Product product;


    @Nested
    @DisplayName("when product has available amount")
    class WhenProductHasAvailableAmount {

        @Test
        @DisplayName("should book amount correctly")
        public void shouldBookAmountCorrectly() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 10, 0);

            assertDoesNotThrow(() -> product.bookAmount(3));
            assertThat(product.getAvailableAmount()).isEqualTo(7);
            assertThat(product.getBookedAmount()).isEqualTo(3);
        }

        @Test
        @DisplayName("should conclude amount decrease correctly")
        public void shouldConcludeAmountDecreaseCorrectly() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 7, 3);

            assertDoesNotThrow(() -> product.concludeAmountDecrease(3));
            assertThat(product.getAvailableAmount()).isEqualTo(7);
            assertThat(product.getBookedAmount()).isEqualTo(0);
        }

        @Test
        @DisplayName("should cancel amount decrease correctly")
        public void shouldCancelAmountDecreaseCorrectly() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 7, 3);

            assertDoesNotThrow(() -> product.cancelAmountDecrease(3));
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(0);
        }
    }


    @Nested
    @DisplayName("when product has no available amount")
    class WhenProductHasNoAvailableAmount {

        @Test
        @DisplayName("should throw when trying to book amount")
        public void shouldThrowWhenTryingToBookAmount() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 3, 0);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.bookAmount(4)
            );

            assertThat(exception.getMessage()).isEqualTo("product is unavailable");
            assertThat(product.getAvailableAmount()).isEqualTo(3);
            assertThat(product.getBookedAmount()).isEqualTo(0);
        }
    }


    @Nested
    @DisplayName("when product has no available booked amount")
    class WhenProductHasNoBookedAmount {

        @Test
        @DisplayName("should throw when trying to conclude amount decrease")
        public void shouldThrowWhenTryingToConcludeAmountDecrease() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 10, 1);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.concludeAmountDecrease(2)
            );

            assertThat(exception.getMessage()).isEqualTo("product is unavailable");
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(1);
        }


        @Test
        @DisplayName("should throw when trying to cancel amount decrease")
        public void shouldThrowWhenTryingToCancelAmountDecrease() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 10, 1);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.cancelAmountDecrease(2)
            );

            assertThat(exception.getMessage()).isEqualTo("product is unavailable");
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(1);
        }
    }


    @Nested
    @DisplayName("when product is inactive")
    class WhenProductIsInactive {

        @Test
        @DisplayName("should throw when trying to book amount")
        public void shouldThrowWhenTryingToBookAmount() {
            var productId = new ProductId("123", null);
            product = new Product(productId, false, 10, 1);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.bookAmount(1)
            );

            assertThat(exception.getMessage()).isEqualTo("product is inactive");
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(1);

        }

        @Test
        @DisplayName("should throw when trying to conclude amount decrease")
        public void shouldThrowWhenTryingToConcludeAmountDecrease() {
            var productId = new ProductId("123", null);
            product = new Product(productId, false, 10, 0);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.concludeAmountDecrease(1)
            );

            assertThat(exception.getMessage()).isEqualTo("product is inactive");
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(0);

        }

        @Test
        @DisplayName("should throw when trying to cancel amount decrease")
        public void shouldThrowWhenTryingToCancelAmountDecrease() {
            var productId = new ProductId("123", null);
            product = new Product(productId, false, 10, 0);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.cancelAmountDecrease(1)
            );

            assertThat(exception.getMessage()).isEqualTo("product is inactive");
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(0);

        }


        @Test
        @DisplayName("should throw when trying to increase available amount")
        public void shouldThrowWhenTryingToIncreaseAvailableAmount() {
            var productId = new ProductId("123", null);
            product = new Product(productId, false, 10, 0);

            var exception = assertThrows(
                    IllegalStateException.class,() -> product.increaseAvailableAmount(1)
            );

            assertThat(exception.getMessage()).isEqualTo("product is inactive");
            assertThat(product.getAvailableAmount()).isEqualTo(10);
            assertThat(product.getBookedAmount()).isEqualTo(0);

        }
    }


    @Nested
    @DisplayName("when product is active")
    class WhenProductIsActive {

        @Test
        @DisplayName("should increase available amount correctly")
        public void shouldIncreaseAvailableAmountCorrectly() {

            var productId = new ProductId("123", null);
            product = new Product(productId, true, 10, 0);

            assertDoesNotThrow(()->product.increaseAvailableAmount(1));

            assertThat(product.getAvailableAmount()).isEqualTo(11);
            assertThat(product.getBookedAmount()).isEqualTo(0);
        }

    }

    @Test
    @DisplayName("should activate product correctly")
    public void shouldActivateProductCorrectly(){
        var productId = new ProductId("123", null);
        product = new Product(productId, false, 10, 0);

        assertDoesNotThrow(()->product.activate());
        assertTrue(product.isActive());
    }

    @Test
    @DisplayName("should deactivate product correctly")
    public void shouldDeactivateProductCorrectly(){
        var productId = new ProductId("123", null);
        product = new Product(productId, true, 10, 0);

        assertDoesNotThrow(()->product.deactivate());
        assertFalse(product.isActive());
    }


}