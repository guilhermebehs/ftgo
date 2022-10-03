package br.com.guilhermebehs.ftgo.payment.domain.commands;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ValidateOrderPaymentCommand {

    private String orderId;

    private String cardNumber;

    private Double price;

    private String customerId;

    private LocalDateTime ocurredOn;


    @Override
    public String toString() {
        return "ValidateOrderPaymentCommand{" +
                "orderId='" + orderId + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", price=" + price +
                ", customerId='" + customerId + '\'' +
                ", ocurredOn=" + ocurredOn +
                '}';
    }
}
