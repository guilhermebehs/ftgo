package br.com.guilhermebehs.ftgo.order.mocks;

import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.ports.ValidateOrderPaymentNotificationService;
import org.springframework.stereotype.Component;

@Component
public class ValidateOrderPaymentNotificationServiceMock implements ValidateOrderPaymentNotificationService {
    @Override
    public void notify(Order order) {

    }
}
