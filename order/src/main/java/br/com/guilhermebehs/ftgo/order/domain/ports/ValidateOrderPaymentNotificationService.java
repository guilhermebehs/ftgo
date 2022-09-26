package br.com.guilhermebehs.ftgo.order.domain.ports;

import br.com.guilhermebehs.ftgo.order.domain.entities.Order;

public interface ValidateOrderPaymentNotificationService {

    public void notify(Order order);
}
