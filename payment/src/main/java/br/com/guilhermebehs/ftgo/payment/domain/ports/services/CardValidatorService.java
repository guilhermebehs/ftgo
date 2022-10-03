package br.com.guilhermebehs.ftgo.payment.domain.ports.services;



public interface CardValidatorService {

    boolean isCreditCardValid(String cardNumber);
}
