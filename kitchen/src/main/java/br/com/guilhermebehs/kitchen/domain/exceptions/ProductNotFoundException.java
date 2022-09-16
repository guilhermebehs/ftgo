package br.com.guilhermebehs.kitchen.domain.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){
        super("product not found");
    }
}
