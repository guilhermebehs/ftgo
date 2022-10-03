package br.com.guilhermebehs.ftgo.order.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.order.domain.events.OrderPaymentDeniedEvent;
import br.com.guilhermebehs.ftgo.order.domain.services.DenyOrderPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderPaymentDeniedEvent {

    private final DenyOrderPaymentService denyOrderPaymentService;
    private final ObjectMapper objectMapper;

    public KafkaOrderPaymentDeniedEvent(DenyOrderPaymentService denyOrderPaymentService,
                                        ObjectMapper objectMapper) {
        this.denyOrderPaymentService = denyOrderPaymentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_payment_denied", groupId = "groupId")
    public void listen(String payload){

        try {
            var orderPaymentDenyedEvent  = objectMapper
                    .readValue(payload, OrderPaymentDeniedEvent.class);

            denyOrderPaymentService.deny(orderPaymentDenyedEvent);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
