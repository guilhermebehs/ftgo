package br.com.guilhermebehs.payment.adapters.out.mongo.mappers;

import br.com.guilhermebehs.payment.adapters.out.mongo.collections.OrderPaymentDocument;
import br.com.guilhermebehs.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.payment.domain.enums.ApprovalStatus;

public class OrderPaymentDocumentMapper {

    public static OrderPaymentDocument fromOrderPayment(OrderPayment orderPayment){
        return new OrderPaymentDocument(
                orderPayment.getOrderId(),
                orderPayment.getCardNumber(),
                orderPayment.getCustomerId(),
                orderPayment.getPrice(),
                orderPayment.getApprovalStatus(),
                orderPayment.getOcurredOn()
        );
    }

    public static OrderPayment toOrderPayment(OrderPaymentDocument orderPaymentDocument){
        var orderPayment = new OrderPayment(
                orderPaymentDocument.getOrderId(),
                orderPaymentDocument.getCardNumber(),
                orderPaymentDocument.getPrice(),
                orderPaymentDocument.getCustomerId(),
                orderPaymentDocument.getOcurredOn()
        );

        if(orderPaymentDocument.getApprovalStatus() == ApprovalStatus.APPROVED)
            orderPayment.approvePayment();
        else
            orderPayment.denyPayment();

        return orderPayment;
    }
}
