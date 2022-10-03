package br.com.guilhermebehs.ftgo.payment.adapters.out;

import br.com.guilhermebehs.ftgo.payment.domain.ports.services.CardValidatorService;
import org.springframework.stereotype.Service;

@Service
public class InMemoryCardValidatorService implements CardValidatorService {

    @Override
    public boolean isCreditCardValid(String cardNumber) {
        var firsDigit = Integer.parseInt(cardNumber.substring(0,1));
        if(firsDigit % 2 == 0)
           return true;
        return false;
    }
}
