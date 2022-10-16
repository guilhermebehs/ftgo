package br.com.guilhermebehs.ftgo.order.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.order.domain.events.OrderDeliveredEvent;
import br.com.guilhermebehs.ftgo.order.domain.services.DeliverOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderDeliveredEvent {

    private final DeliverOrderService deliverOrderService;
    private final ObjectMapper objectMapper;

    public KafkaOrderDeliveredEvent(DeliverOrderService deliverOrderService,
                                    ObjectMapper objectMapper) {
        this.deliverOrderService = deliverOrderService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_delivered", groupId = "orderGroupId")
    public void listen(String payload){

        try {
           var orderDeliveredEvent = objectMapper
                    .readValue(payload, OrderDeliveredEvent.class);

            deliverOrderService.deliver(orderDeliveredEvent.getOrderId());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
