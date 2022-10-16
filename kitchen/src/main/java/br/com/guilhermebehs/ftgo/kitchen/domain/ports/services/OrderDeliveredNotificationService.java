package br.com.guilhermebehs.ftgo.kitchen.domain.ports.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderDeliveredEvent;

public interface OrderDeliveredNotificationService {

    void notify(OrderDeliveredEvent orderDeliveredEvent);
}
