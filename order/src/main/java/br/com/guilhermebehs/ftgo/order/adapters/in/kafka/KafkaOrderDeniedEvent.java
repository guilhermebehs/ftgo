package br.com.guilhermebehs.ftgo.order.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.order.domain.events.OrderDeniedEvent;
import br.com.guilhermebehs.ftgo.order.domain.services.DenyOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderDeniedEvent {

    private final DenyOrderService denyOrderService;
    private final ObjectMapper objectMapper;

    public KafkaOrderDeniedEvent(DenyOrderService denyOrderService,
                                 ObjectMapper objectMapper) {
        this.denyOrderService = denyOrderService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_denied", groupId = "orderGroupId")
    public void listen(String payload){

        try {
           var orderDeniedEvent = objectMapper
                    .readValue(payload, OrderDeniedEvent.class);

            denyOrderService.deny(orderDeniedEvent.getOrderId());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
