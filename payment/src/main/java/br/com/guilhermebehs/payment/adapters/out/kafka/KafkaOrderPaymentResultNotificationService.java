package br.com.guilhermebehs.payment.adapters.out.kafka;

import br.com.guilhermebehs.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.payment.domain.enums.ApprovalStatus;
import br.com.guilhermebehs.payment.domain.ports.services.OrderPaymentResultNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static br.com.guilhermebehs.payment.domain.enums.ApprovalStatus.APPROVED;

@Service
public class KafkaOrderPaymentResultNotificationService implements OrderPaymentResultNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaOrderPaymentResultNotificationService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void notify(OrderPayment orderPayment) {
        try {
            String topic = null;
            if(orderPayment.getApprovalStatus() == APPROVED)
                topic = "order_payment_approved";
            else
                topic = "order_payment_denied";

            var message = objectMapper.writeValueAsString(orderPayment);
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
