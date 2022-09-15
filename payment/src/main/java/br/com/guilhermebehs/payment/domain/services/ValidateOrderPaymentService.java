package br.com.guilhermebehs.payment.domain.services;

import br.com.guilhermebehs.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.payment.domain.commands.ValidateOrderPaymentCommand;
import br.com.guilhermebehs.payment.domain.mappers.OrderPaymentMapper;
import br.com.guilhermebehs.payment.domain.ports.repositories.OrderPaymentRepository;
import br.com.guilhermebehs.payment.domain.ports.services.CardValidatorService;
import br.com.guilhermebehs.payment.domain.ports.services.OrderPaymentResultNotificationService;
import org.springframework.stereotype.Service;

@Service
public class ValidateOrderPaymentService {

    private final OrderPaymentRepository orderPaymentRepository;
    private final CardValidatorService cardValidatorService;
    private final OrderPaymentResultNotificationService orderPaymentResultNotificationService;
    private final OrderPaymentMapper orderPaymentMapper;

    public ValidateOrderPaymentService(OrderPaymentRepository orderPaymentRepository,
                                       CardValidatorService cardValidatorService,
                                       OrderPaymentResultNotificationService orderPaymentResultNotificationService,
                                       OrderPaymentMapper orderPaymentMapper) {

        this.orderPaymentRepository = orderPaymentRepository;
        this.cardValidatorService = cardValidatorService;
        this.orderPaymentResultNotificationService = orderPaymentResultNotificationService;
        this.orderPaymentMapper = orderPaymentMapper;
    }

    public void validate(ValidateOrderPaymentCommand validateOrderPaymentCommand){

        OrderPayment orderPayment = null;

        var orderPaymentOp = orderPaymentRepository
                .getByOrderId(validateOrderPaymentCommand.getOrderId());
        if(orderPaymentOp.isPresent()) {
            orderPayment = orderPaymentOp.get();
            validateAndSetPossibleChanges(orderPayment, validateOrderPaymentCommand);
        }
        else
            orderPayment = orderPaymentMapper.fromValidateOrderPaymentCommand(validateOrderPaymentCommand);


       if(isCreditCardValid(validateOrderPaymentCommand.getCardNumber()))
          orderPayment.approvePayment();
       else
          orderPayment.denyPayment();

        orderPaymentRepository.save(orderPayment);
        orderPaymentResultNotificationService.notify(orderPayment);
    }

    private void validateAndSetPossibleChanges(OrderPayment orderPayment,
                                         ValidateOrderPaymentCommand validateOrderPaymentCommand){

        if (!orderPayment.getCardNumber().equals(validateOrderPaymentCommand.getCardNumber()))
            orderPayment.changeCardNumber(validateOrderPaymentCommand.getCardNumber());

        if (!orderPayment.getPrice().equals(validateOrderPaymentCommand.getPrice()))
            orderPayment.changePrice(validateOrderPaymentCommand.getPrice());
    }


    private boolean isCreditCardValid(String cardNumber){
        return cardValidatorService.isCreditCardValid(cardNumber);
    }
}
