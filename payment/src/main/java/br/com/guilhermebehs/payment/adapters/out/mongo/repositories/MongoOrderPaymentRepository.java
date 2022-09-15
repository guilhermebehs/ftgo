package br.com.guilhermebehs.payment.adapters.out.mongo.repositories;

import br.com.guilhermebehs.payment.adapters.out.mongo.collections.OrderPaymentDocument;
import br.com.guilhermebehs.payment.adapters.out.mongo.mappers.OrderPaymentDocumentMapper;
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
        mongoTemplate.save(OrderPaymentDocumentMapper.fromOrderPayment(orderPayment));
    }

    @Override
    public Optional<OrderPayment> getByOrderId(String orderId) {
        var orderPaymentDocument = mongoTemplate.findById(orderId, OrderPaymentDocument.class);
        if(orderPaymentDocument == null)
           return Optional.empty();
        return Optional.of(OrderPaymentDocumentMapper.toOrderPayment(orderPaymentDocument));
    }
}
