package br.com.guilhermebehs.ftgo.order.adapters.out.kafka;

import br.com.guilhermebehs.ftgo.order.domain.commands.ValidateOrderPaymentCommand;
import br.com.guilhermebehs.ftgo.order.domain.ports.ValidateOrderPaymentNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaValidateOrderPaymentNotificationService implements ValidateOrderPaymentNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaValidateOrderPaymentNotificationService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void notify(ValidateOrderPaymentCommand validateOrderPaymentCommand) {
        try {
            String topic = "validate_order_payment";

            var message = objectMapper.writeValueAsString(validateOrderPaymentCommand);
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
