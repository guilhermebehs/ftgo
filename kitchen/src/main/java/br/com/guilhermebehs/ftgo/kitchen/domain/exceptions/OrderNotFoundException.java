package br.com.guilhermebehs.ftgo.kitchen.domain.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(){
        super("order not found");
    }
}
