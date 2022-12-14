package br.com.guilhermebehs.ftgo.order.domain.events;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderPaymentDeniedEvent {

    private String orderId;
    private LocalDateTime ocurredOn;


    @Override
    public String toString() {
        return "OrderPaymentApprovedEvent{" +
                "orderId='" + orderId + '\'' +
                ", ocurredOn=" + ocurredOn +
                '}';
    }
}
