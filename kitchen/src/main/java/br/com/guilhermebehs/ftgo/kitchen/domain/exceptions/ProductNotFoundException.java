package br.com.guilhermebehs.ftgo.kitchen.domain.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){
        super("product not found");
    }
}
