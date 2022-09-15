package br.com.guilhermebehs.payment.domain.entities;

import br.com.guilhermebehs.payment.domain.enums.ApprovalStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderPayment {

    private String orderId;

    private String cardNumber;

    private Double price;

    private String customerId;

    private ApprovalStatus approvalStatus;

    private LocalDateTime ocurredOn;

    public OrderPayment(String orderId, String cardNumber, Double price, String customerId, LocalDateTime ocurredOn){

        validateOrderId(orderId);
        validateCardNumber(cardNumber);
        validatePrice(price);
        validateCustomerId(customerId);

        this.orderId = orderId;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.price = price;
        this.ocurredOn = ocurredOn;
    }


    public void changeCardNumber(String cardNumber){
        validateCardNumber(cardNumber);
        this.cardNumber = cardNumber;
    }

    public void changePrice( Double price){
       validatePrice(price);
       this.price = price;
    }

    public void approvePayment(){
        this.approvalStatus = ApprovalStatus.APPROVED;
    }

    public void denyPayment(){
        this.approvalStatus = ApprovalStatus.DENIED;
    }

    private void validateOrderId(String orderId) {
       if(orderId == null || orderId.trim().isEmpty())
           throw new IllegalArgumentException("Invalid order id");
    }

    private void validateCardNumber(String cardNumber) {
        if(cardNumber == null || cardNumber.trim().isEmpty() ||
          !(cardNumber.length()  >= 4 && cardNumber.length()  <= 8) ||
            doesCardNumberContainsLetters(cardNumber))
            throw new IllegalArgumentException("Invalid card number");
    }

    private void validatePrice(Double price) {
        if(price == null || price.doubleValue() == 0)
            throw new IllegalArgumentException("Invalid price");
    }


    private void validateCustomerId(String customerId) {
        if(customerId == null || customerId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid customer id");
    }


    private boolean doesCardNumberContainsLetters(String cardNumber){
        try {
            Long.parseLong(cardNumber);
            return false;
        }
        catch (NumberFormatException e) {
            return true;
        }
    }

}
