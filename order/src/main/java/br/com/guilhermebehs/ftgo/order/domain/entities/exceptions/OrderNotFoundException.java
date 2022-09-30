package br.com.guilhermebehs.ftgo.order.domain.entities.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String description) {
        super(description);
    }
}
