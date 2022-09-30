package br.com.guilhermebehs.ftgo.order.domain.ports;

import br.com.guilhermebehs.ftgo.order.domain.entities.Order;

import java.util.Optional;

public interface OrderRepository {

    String save(Order order);

    Optional<Order> findById(String orderId);
}
