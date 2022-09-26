package br.com.guilhermebehs.ftgo.order.domain.entities.exceptions;

public class InvalidItemException extends RuntimeException{

    public InvalidItemException(String description) {
        super(description);
    }
}
