package br.com.guilhermebehs.ftgo.kitchen.domain.ports.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.events.OrderDeniedEvent;

public interface OrderDeniedNotificationService {

    public void notify(OrderDeniedEvent orderDeniedEvent);
}
