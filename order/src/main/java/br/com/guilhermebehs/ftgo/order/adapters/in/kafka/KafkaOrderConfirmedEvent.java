package br.com.guilhermebehs.ftgo.order.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.order.domain.events.OrderConfirmedEvent;
import br.com.guilhermebehs.ftgo.order.domain.services.ConfirmOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderConfirmedEvent {

    private final ConfirmOrderService confirmOrderService;
    private final ObjectMapper objectMapper;

    public KafkaOrderConfirmedEvent(ConfirmOrderService confirmOrderService,
                                    ObjectMapper objectMapper) {
        this.confirmOrderService = confirmOrderService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_confirmed", groupId = "orderGroupId")
    public void listen(String payload){

        try {
           var orderConfirmedEvent = objectMapper
                    .readValue(payload, OrderConfirmedEvent.class);

            confirmOrderService.confirm(orderConfirmedEvent.getOrderId());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
