package br.com.guilhermebehs.ftgo.payment.domain.ports.repositories;


import br.com.guilhermebehs.ftgo.payment.domain.entities.OrderPayment;

import java.util.Optional;

public interface OrderPaymentRepository {
    void save(OrderPayment orderPayment);
    Optional<OrderPayment> getByOrderId(String orderId);
}
