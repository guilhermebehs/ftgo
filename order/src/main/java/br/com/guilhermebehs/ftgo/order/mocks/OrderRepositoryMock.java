package br.com.guilhermebehs.ftgo.order.mocks;

import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryMock implements OrderRepository {
    @Override
    public String create(Order order) {
        return null;
    }
}
