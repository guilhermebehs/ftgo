package br.com.guilhermebehs.payment.domain.ports.repositories;


import br.com.guilhermebehs.payment.domain.entities.OrderPayment;

import java.util.Optional;

public interface OrderPaymentRepository {
    void save(OrderPayment orderPayment);
    Optional<OrderPayment> getByOrderId(String orderId);
}
