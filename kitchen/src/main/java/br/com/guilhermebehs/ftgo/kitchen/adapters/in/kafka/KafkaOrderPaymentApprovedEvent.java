package br.com.guilhermebehs.ftgo.kitchen.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.ftgo.kitchen.domain.services.CreateOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderPaymentApprovedEvent {

    private final CreateOrderService createOrderService;
    private final ObjectMapper objectMapper;

    public KafkaOrderPaymentApprovedEvent(CreateOrderService createOrderService,
                                          ObjectMapper objectMapper) {
        this.createOrderService = createOrderService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_payment_approved", groupId = "kitchenGroupId")
    public void listen(String payload){

        try {
           var orderPaymentApprovedEvent = objectMapper
                    .readValue(payload, OrderPaymentApprovedEvent.class);

            createOrderService.create(orderPaymentApprovedEvent);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
