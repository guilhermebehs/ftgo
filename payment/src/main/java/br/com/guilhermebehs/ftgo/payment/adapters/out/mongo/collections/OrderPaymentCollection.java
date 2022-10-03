package br.com.guilhermebehs.ftgo.payment.adapters.out.mongo.collections;

import br.com.guilhermebehs.ftgo.payment.domain.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "order_payment")
@Getter
@Setter
@AllArgsConstructor
public class OrderPaymentCollection {

    @Id
    private String orderId;

    @Field(name = "card_number")
    private String cardNumber;

    @Field(name = "custome_id")
    private String customerId;

    @Field(name = "price")
    private Double price;

    @Field(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @Field(name = "ocurred_on")
    private LocalDateTime ocurredOn;

}
