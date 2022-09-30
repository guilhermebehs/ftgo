package br.com.guilhermebehs.ftgo.order.adapters.out.repositories;

import br.com.guilhermebehs.ftgo.order.adapters.out.collections.OrderCollection;
import br.com.guilhermebehs.ftgo.order.adapters.out.mappers.OrderCollectionMapper;
import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoOrderRepository implements OrderRepository {

    private final MongoTemplate mongoTemplate;

    public MongoOrderRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Order> findById(String orderId) {

        var orderCollection = mongoTemplate.findById(orderId, OrderCollection.class);

        if(orderCollection == null)
            return Optional.empty();

        return Optional.of(OrderCollectionMapper.toOrder(orderCollection));
    }


    @Override
    public String save(Order order) {
        var orderCollection = mongoTemplate.save(OrderCollectionMapper.fromOrder(order));
        return OrderCollectionMapper.toOrder(orderCollection).getOrderId();
    }
}
