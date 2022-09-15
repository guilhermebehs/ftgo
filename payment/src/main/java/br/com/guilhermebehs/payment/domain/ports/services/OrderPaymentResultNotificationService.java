package br.com.guilhermebehs.payment.domain.ports.services;

import br.com.guilhermebehs.payment.domain.entities.OrderPayment;

public interface OrderPaymentResultNotificationService {

    void notify(OrderPayment orderPayment);
}
