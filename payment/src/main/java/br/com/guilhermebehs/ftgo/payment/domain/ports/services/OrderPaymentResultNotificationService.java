package br.com.guilhermebehs.ftgo.payment.domain.ports.services;

import br.com.guilhermebehs.ftgo.payment.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.ftgo.payment.domain.events.OrderPaymentDeniedEvent;

public interface OrderPaymentResultNotificationService {

    void notifyApproved(OrderPaymentApprovedEvent orderPaymentApprovedEvent);

    void notifyDenied(OrderPaymentDeniedEvent orderPaymentDeniedEvent);
}
