package br.com.guilhermebehs.payment.domain.ports.services;

import br.com.guilhermebehs.payment.domain.events.OrderPaymentApprovedEvent;
import br.com.guilhermebehs.payment.domain.events.OrderPaymentDeniedEvent;

public interface OrderPaymentResultNotificationService {

    void notifyApproved(OrderPaymentApprovedEvent orderPaymentApprovedEvent);

    void notifyDenied(OrderPaymentDeniedEvent orderPaymentDeniedEvent);
}
