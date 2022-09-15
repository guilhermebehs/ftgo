package br.com.guilhermebehs.payment.mocks;

import br.com.guilhermebehs.payment.domain.commands.ValidateOrderPaymentCommand;

import java.time.LocalDateTime;
public class ValidateOrderPaymentCommandMock {


    public static ValidateOrderPaymentCommand mockWithInvalidCreditCard(){

        return new ValidateOrderPaymentCommand(
                OrderPaymentMock.mock().getOrderId(),
                OrderPaymentMock.mock().getCardNumber(),
                OrderPaymentMock.mock().getPrice(),
                OrderPaymentMock.mock().getCustomerId(),
                OrderPaymentMock.mock().getOcurredOn()
        );
    }

    public static ValidateOrderPaymentCommand mockWithValidCreditCard(){
        return new ValidateOrderPaymentCommand(
                OrderPaymentMock.mock().getOrderId(),
                OrderPaymentMock.mock().getCardNumber(),
                OrderPaymentMock.mock().getPrice(),
                OrderPaymentMock.mock().getCustomerId(),
                OrderPaymentMock.mock().getOcurredOn()
        );
    }
}
