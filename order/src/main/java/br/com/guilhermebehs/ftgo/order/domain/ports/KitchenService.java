package br.com.guilhermebehs.ftgo.order.domain.ports;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.FindProductDto;

import java.util.Optional;

public interface KitchenService {

    Optional<FindProductDto> getProductByNameAndKitchen(String name, String kitchen);
}
