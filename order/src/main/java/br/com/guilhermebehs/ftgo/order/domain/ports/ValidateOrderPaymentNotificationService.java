package br.com.guilhermebehs.ftgo.order.domain.ports;

import br.com.guilhermebehs.ftgo.order.domain.commands.ValidateOrderPaymentCommand;

public interface ValidateOrderPaymentNotificationService {

     void notify(ValidateOrderPaymentCommand validateOrderPaymentCommand);
}
