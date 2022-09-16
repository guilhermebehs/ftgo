package br.com.guilhermebehs.kitchen.adapters.out.repositories;

import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductCollection;
import br.com.guilhermebehs.kitchen.adapters.out.collections.ProductId;
import br.com.guilhermebehs.kitchen.adapters.out.mappers.ProductCollectionMapper;
import br.com.guilhermebehs.kitchen.domain.entities.Product;
import br.com.guilhermebehs.kitchen.domain.entities.ProductCollectionId;
import br.com.guilhermebehs.kitchen.domain.ports.repositories.ProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoProductRepository implements ProductRepository {

    private final MongoTemplate mongoTemplate;

    public MongoProductRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Optional<Product> findById(ProductId productId) {

        var productCollectionId = new ProductCollectionId(productId.getProduct(), productId.getKitchen());
        var productCollection = mongoTemplate.findById(productCollectionId, ProductCollection.class);

        if(productCollection == null)
            return Optional.empty();

        return Optional.of(ProductCollectionMapper.toProduct(productCollection));
    }

    @Override
    public Product save(Product product) {
        var productCollection = mongoTemplate.save(ProductCollectionMapper.fromProduct(product));
        return ProductCollectionMapper.toProduct(productCollection);
    }
}
