package br.com.guilhermebehs.ftgo.order.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.order.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.ftgo.order.domain.services.ApproveOrderPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderPaymentApprovedEvent {

    private final ApproveOrderPaymentService approveOrderPaymentService;
    private final ObjectMapper objectMapper;

    public KafkaOrderPaymentApprovedEvent(ApproveOrderPaymentService approveOrderPaymentService,
                                          ObjectMapper objectMapper) {
        this.approveOrderPaymentService = approveOrderPaymentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_payment_approved", groupId = "groupId")
    public void listen(String payload){

        OrderPaymentApprovedEvent orderPaymentApprovedEvent = null;
        try {
            orderPaymentApprovedEvent = objectMapper
                    .readValue(payload, OrderPaymentApprovedEvent.class);

            approveOrderPaymentService.approve(orderPaymentApprovedEvent);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
