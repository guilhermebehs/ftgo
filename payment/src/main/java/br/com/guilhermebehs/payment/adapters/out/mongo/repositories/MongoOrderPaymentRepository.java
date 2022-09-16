package br.com.guilhermebehs.payment.adapters.out.mongo.repositories;

import br.com.guilhermebehs.payment.adapters.out.mongo.collections.OrderPaymentCollection;
import br.com.guilhermebehs.payment.adapters.out.mongo.mappers.OrderPaymentCollectionMapper;
import br.com.guilhermebehs.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.payment.domain.ports.repositories.OrderPaymentRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoOrderPaymentRepository implements OrderPaymentRepository {

    private final MongoTemplate mongoTemplate;

    public MongoOrderPaymentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(OrderPayment orderPayment) {
        mongoTemplate.save(OrderPaymentCollectionMapper.fromOrderPayment(orderPayment));
    }

    @Override
    public Optional<OrderPayment> getByOrderId(String orderId) {
        var orderPaymentCollection = mongoTemplate.findById(orderId, OrderPaymentCollection.class);
        if(orderPaymentCollection == null)
           return Optional.empty();
        return Optional.of(OrderPaymentCollectionMapper.toOrderPayment(orderPaymentCollection));
    }
}
