package br.com.guilhermebehs.ftgo.kitchen.domain.ports.services;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.dtos.OrderDetailsDto;

public interface OrderService {

     OrderDetailsDto getOrderDetails(String id);
}
