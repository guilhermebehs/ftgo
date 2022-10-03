package br.com.guilhermebehs.ftgo.kitchen.adapters.out.collections;

import br.com.guilhermebehs.ftgo.kitchen.domain.entities.ProductCollectionId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "product")
@Getter
@Setter
@AllArgsConstructor
public class ProductCollection {

    @Id
    private ProductCollectionId productId;

    @Field("is_active")
    private boolean isActive;

    @Field("available_amount")
    private int availableAmount;

    @Field("booked_mount")
    private int bookedAmount;
}
