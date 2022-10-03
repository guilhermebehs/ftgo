package br.com.guilhermebehs.ftgo.payment.domain.mappers;

import br.com.guilhermebehs.ftgo.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.ftgo.payment.domain.commands.ValidateOrderPaymentCommand;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMapper {

    public OrderPayment fromValidateOrderPaymentCommand(
            ValidateOrderPaymentCommand validateOrderPaymentCommand){
        return new OrderPayment(
                validateOrderPaymentCommand.getOrderId(),
                validateOrderPaymentCommand.getCardNumber(),
                validateOrderPaymentCommand.getPrice(),
                validateOrderPaymentCommand.getCustomerId(),
                validateOrderPaymentCommand.getOcurredOn()
        );
    }
}
