package br.com.guilhermebehs.payment.adapters.out.mongo.mappers;

import br.com.guilhermebehs.payment.adapters.out.mongo.collections.OrderPaymentCollection;
import br.com.guilhermebehs.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.payment.domain.enums.ApprovalStatus;

public class OrderPaymentCollectionMapper {

    public static OrderPaymentCollection fromOrderPayment(OrderPayment orderPayment){
        return new OrderPaymentCollection(
                orderPayment.getOrderId(),
                orderPayment.getCardNumber(),
                orderPayment.getCustomerId(),
                orderPayment.getPrice(),
                orderPayment.getApprovalStatus(),
                orderPayment.getOcurredOn()
        );
    }

    public static OrderPayment toOrderPayment(OrderPaymentCollection orderPaymentCollection){
        var orderPayment = new OrderPayment(
                orderPaymentCollection.getOrderId(),
                orderPaymentCollection.getCardNumber(),
                orderPaymentCollection.getPrice(),
                orderPaymentCollection.getCustomerId(),
                orderPaymentCollection.getOcurredOn()
        );

        if(orderPaymentCollection.getApprovalStatus() == ApprovalStatus.APPROVED)
            orderPayment.approvePayment();
        else
            orderPayment.denyPayment();

        return orderPayment;
    }
}
