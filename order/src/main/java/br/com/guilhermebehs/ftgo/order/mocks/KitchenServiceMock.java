package br.com.guilhermebehs.ftgo.order.mocks;

import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.FindProductDto;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KitchenServiceMock implements KitchenService {


    @Override
    public Optional<FindProductDto> getProductByNameAndKitchen(String name, String kitchen) {
        return Optional.of(new FindProductDto(
                "teste",
                "teste",
                true,
                10,
                0
        ));
    }
}
