package br.com.guilhermebehs.ftgo.kitchen.domain.ports.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderConfirmedEvent;

public interface OrderConfirmedNotificationService {

    public void notify(OrderConfirmedEvent orderConfirmedEvent);
}
