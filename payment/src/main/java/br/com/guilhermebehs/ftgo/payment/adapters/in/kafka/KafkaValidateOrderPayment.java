package br.com.guilhermebehs.ftgo.payment.adapters.in.kafka;

import br.com.guilhermebehs.ftgo.payment.domain.commands.ValidateOrderPaymentCommand;
import br.com.guilhermebehs.ftgo.payment.domain.services.ValidateOrderPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaValidateOrderPayment {

    private final ValidateOrderPaymentService validateOrderPaymentService;
    private final ObjectMapper objectMapper;

    public KafkaValidateOrderPayment(ValidateOrderPaymentService validateOrderPaymentService,
                                     ObjectMapper objectMapper) {
        this.validateOrderPaymentService = validateOrderPaymentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "validate_order_payment", groupId = "groupId")
    public void listen(String payload){
        try {
            var validateOrderPaymentCommand = objectMapper
                    .readValue(payload, ValidateOrderPaymentCommand.class);

            validateOrderPaymentService.validate(validateOrderPaymentCommand);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
