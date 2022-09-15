package br.com.guilhermebehs.payment.domain.ports.services;



public interface CardValidatorService {

    boolean isCreditCardValid(String cardNumber);
}
