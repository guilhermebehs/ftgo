package br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Order;

import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(String id);
    Order save(Order order);
}
