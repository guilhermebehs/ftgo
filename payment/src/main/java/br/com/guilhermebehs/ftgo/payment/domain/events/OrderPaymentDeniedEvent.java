package br.com.guilhermebehs.ftgo.payment.domain.events;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderPaymentDeniedEvent {

    private String orderId;
    private LocalDateTime ocurredOn;

    @Override
    public String toString() {
        return "OrderPaymentDeniedEvent{" +
                "orderId='" + orderId + '\'' +
                ", ocurredOn=" + ocurredOn +
                '}';
    }
}
