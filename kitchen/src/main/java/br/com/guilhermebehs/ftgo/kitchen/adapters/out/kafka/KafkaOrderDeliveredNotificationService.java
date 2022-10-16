package br.com.guilhermebehs.ftgo.kitchen.adapters.out.kafka;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderDeliveredEvent;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.services.OrderDeliveredNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaOrderDeliveredNotificationService implements OrderDeliveredNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaOrderDeliveredNotificationService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void notify(OrderDeliveredEvent orderDeliveredEvent) {
        try {
            String topic = "order_delivered";

            var message = objectMapper.writeValueAsString(orderDeliveredEvent);
            kafkaTemplate.send(topic, message).addCallback(
                    new ListenableFutureCallback<SendResult<String, String>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            ex.printStackTrace();
                        }

                        @Override
                        public void onSuccess(SendResult<String, String> result) {
                            System.out.println("message sent!");
                        }
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
