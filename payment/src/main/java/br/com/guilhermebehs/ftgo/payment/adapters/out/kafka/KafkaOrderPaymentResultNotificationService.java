package br.com.guilhermebehs.ftgo.payment.adapters.out.kafka;

import br.com.guilhermebehs.ftgo.payment.domain.ports.services.OrderPaymentResultNotificationService;
import br.com.guilhermebehs.ftgo.payment.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.ftgo.payment.domain.events.OrderPaymentDeniedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaOrderPaymentResultNotificationService implements OrderPaymentResultNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaOrderPaymentResultNotificationService(KafkaTemplate<String, String> kafkaTemplate,
                                                      ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void notifyApproved(OrderPaymentApprovedEvent orderPaymentApprovedEvent) {
        try {

            var topic = "order_payment_approved";

            var message = objectMapper.writeValueAsString(orderPaymentApprovedEvent);

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


    @Override
    public void notifyDenied(OrderPaymentDeniedEvent orderPaymentDeniedEvent) {
        try {

            var topic = "order_payment_denied";

            var message = objectMapper.writeValueAsString(orderPaymentDeniedEvent);

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
