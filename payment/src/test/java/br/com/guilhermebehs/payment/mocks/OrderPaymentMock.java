package br.com.guilhermebehs.payment.mocks;

import br.com.guilhermebehs.ftgo.payment.domain.entities.OrderPayment;

import java.time.LocalDateTime;

public class OrderPaymentMock {


    public static OrderPayment mock() {

        return new OrderPayment(
                "any order id",
                "12345",
                20.0,
                "123",
                LocalDateTime.parse("2022-09-15T14:38:59.57")
        );
    }

}
