package br.com.guilhermebehs.ftgo.order.domain.entities.enums;

public enum OrderStatus {

    PAYMENT_PENDING,
    PAYMENT_APPROVED,
    PAYMENT_DENIED,
    PREPARING,
    DENIED_BY_KITCHEN,
    CANCELED,
    NO_STOCK,
    DELIVERED;

}
