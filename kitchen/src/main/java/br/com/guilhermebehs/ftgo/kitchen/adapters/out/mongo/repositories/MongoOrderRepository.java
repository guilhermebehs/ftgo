package br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.repositories;

import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections.OrderCollection;
import br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.mappers.OrderCollectionMapper;
import br.com.guilhermebehs.ftgo.kitchen.domain.entities.Order;
import br.com.guilhermebehs.ftgo.kitchen.domain.ports.repositories.OrderRepository;
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
    public Optional<Order> findById(String id) {

        var orderCollection = mongoTemplate.findById(id, OrderCollection.class);

        if(orderCollection == null)
            return Optional.empty();

        return Optional.of(OrderCollectionMapper.toOrder(orderCollection));
    }

    @Override
    public Order save(Order order) {
        var orderCollection = mongoTemplate.save(OrderCollectionMapper.fromOrder(order));
        return OrderCollectionMapper.toOrder(orderCollection);
    }
}
