package br.com.guilhermebehs.payment.domain.services;

import br.com.guilhermebehs.ftgo.payment.domain.entities.OrderPayment;
import br.com.guilhermebehs.ftgo.payment.domain.mappers.OrderPaymentMapper;
import br.com.guilhermebehs.ftgo.payment.domain.ports.repositories.OrderPaymentRepository;
import br.com.guilhermebehs.ftgo.payment.domain.ports.services.CardValidatorService;
import br.com.guilhermebehs.ftgo.payment.domain.ports.services.OrderPaymentResultNotificationService;
import br.com.guilhermebehs.ftgo.payment.domain.services.ValidateOrderPaymentService;
import br.com.guilhermebehs.payment.mocks.OrderPaymentMock;
import br.com.guilhermebehs.payment.mocks.ValidateOrderPaymentCommandMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static br.com.guilhermebehs.ftgo.payment.domain.enums.ApprovalStatus.APPROVED;
import static br.com.guilhermebehs.ftgo.payment.domain.enums.ApprovalStatus.DENIED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ValidateOrderPaymentService.class)
@DisplayName("ValidateOrderPaymentService")
public class ValidateOrderPaymentServiceTest {

    @Autowired
    ValidateOrderPaymentService validateOrderPaymentService;

    @MockBean
    OrderPaymentRepository orderPaymentRepository;

    @MockBean
    CardValidatorService cardValidatorService;

    @MockBean
    OrderPaymentResultNotificationService orderPaymentResultNotificationService;

    @MockBean
    OrderPaymentMapper orderPaymentMapper;

    OrderPayment orderPaymentMock;

    @BeforeEach
    public void setUp(){
        when(orderPaymentRepository.getByOrderId(anyString())).thenReturn(Optional.empty());
        when(cardValidatorService.isCreditCardValid(anyString())).thenReturn(true);

        orderPaymentMock = OrderPaymentMock.mock();
        when(orderPaymentMapper.fromValidateOrderPaymentCommand(any())).thenReturn(orderPaymentMock);
    }

    @Nested
    @DisplayName("when first attempt")
    class WhenFirstAttempt{

        @Test
        @DisplayName("should approve payment when credit card is valid")
        public void shouldApprovePaymentWhenCreditCardIsNotValid(){

            var commandMock = ValidateOrderPaymentCommandMock.mockWithInvalidCreditCard();
            validateOrderPaymentService.validate(commandMock);

            assertThat(orderPaymentMock.getOrderId()).isEqualTo(commandMock.getOrderId());
            assertThat(orderPaymentMock.getCardNumber()).isEqualTo(commandMock.getCardNumber());
            assertThat(orderPaymentMock.getCustomerId()).isEqualTo(commandMock.getCustomerId());
            assertThat(orderPaymentMock.getPrice()).isEqualTo(commandMock.getPrice());
            assertThat(orderPaymentMock.getOcurredOn()).isEqualTo(commandMock.getOcurredOn());
            assertThat(orderPaymentMock.getApprovalStatus()).isEqualTo(APPROVED);

            verify(orderPaymentRepository, times(1)).getByOrderId(commandMock.getOrderId());
            verify(orderPaymentRepository, times(1)).save(orderPaymentMock);
            verify(orderPaymentMapper, times(1)).fromValidateOrderPaymentCommand(commandMock);
            verify(orderPaymentResultNotificationService, times(0)).notifyDenied(any());
            verify(orderPaymentResultNotificationService, times(1)).notifyApproved(any());

            verify(cardValidatorService, times(1)).isCreditCardValid(commandMock.getCardNumber());

        }

        @Test
        @DisplayName("should deny payment when credit card is not valid")
        public void shouldDenyPaymentWhenCreditCardIsNotValid(){

            when(cardValidatorService.isCreditCardValid(anyString())).thenReturn(false);

            var commandMock = ValidateOrderPaymentCommandMock.mockWithInvalidCreditCard();
            validateOrderPaymentService.validate(commandMock);

            assertThat(orderPaymentMock.getOrderId()).isEqualTo(commandMock.getOrderId());
            assertThat(orderPaymentMock.getCardNumber()).isEqualTo(commandMock.getCardNumber());
            assertThat(orderPaymentMock.getCustomerId()).isEqualTo(commandMock.getCustomerId());
            assertThat(orderPaymentMock.getPrice()).isEqualTo(commandMock.getPrice());
            assertThat(orderPaymentMock.getOcurredOn()).isEqualTo(commandMock.getOcurredOn());
            assertThat(orderPaymentMock.getApprovalStatus()).isEqualTo(DENIED);

            verify(orderPaymentRepository, times(1)).getByOrderId(commandMock.getOrderId());
            verify(orderPaymentRepository, times(1)).save(orderPaymentMock);
            verify(orderPaymentMapper, times(1)).fromValidateOrderPaymentCommand(commandMock);
            verify(orderPaymentResultNotificationService, times(1)).notifyDenied(any());
            verify(orderPaymentResultNotificationService, times(0)).notifyApproved(any());

            verify(cardValidatorService, times(1)).isCreditCardValid(commandMock.getCardNumber());

        }

    }

    @Nested
    @DisplayName("when recurrence")
    class whenRecurrence{

        @Nested
        @DisplayName("when no data has changed")
        class whenNoDataHasChanged{

            @Test
            @DisplayName("should approve payment when credit card is valid")
            public void shouldApprovePaymentWhenCreditCardIsValid(){

                when(orderPaymentRepository.getByOrderId(anyString())).thenReturn(Optional.of(orderPaymentMock));

                var commandMock = ValidateOrderPaymentCommandMock.mockWithInvalidCreditCard();
                validateOrderPaymentService.validate(commandMock);

                assertThat(orderPaymentMock.getOrderId()).isEqualTo(commandMock.getOrderId());
                assertThat(orderPaymentMock.getCardNumber()).isEqualTo(commandMock.getCardNumber());
                assertThat(orderPaymentMock.getCustomerId()).isEqualTo(commandMock.getCustomerId());
                assertThat(orderPaymentMock.getPrice()).isEqualTo(commandMock.getPrice());
                assertThat(orderPaymentMock.getOcurredOn()).isEqualTo(commandMock.getOcurredOn());
                assertThat(orderPaymentMock.getApprovalStatus()).isEqualTo(APPROVED);

                verify(orderPaymentRepository, times(1)).getByOrderId(commandMock.getOrderId());
                verify(orderPaymentRepository, times(1)).save(orderPaymentMock);
                verify(orderPaymentResultNotificationService, times(0)).notifyDenied(any());
                verify(orderPaymentResultNotificationService, times(1)).notifyApproved(any());

                verify(orderPaymentMapper, times(0)).fromValidateOrderPaymentCommand(commandMock);
                verify(cardValidatorService, times(1)).isCreditCardValid(commandMock.getCardNumber());

            }

            @Test
            @DisplayName("should deny payment when credit card is not valid")
            public void shouldDenyPaymentWhenCreditCardIsNotValid(){

                when(cardValidatorService.isCreditCardValid(anyString())).thenReturn(false);


                when(orderPaymentRepository.getByOrderId(anyString())).thenReturn(Optional.of(orderPaymentMock));

                var commandMock = ValidateOrderPaymentCommandMock.mockWithInvalidCreditCard();
                validateOrderPaymentService.validate(commandMock);

                assertThat(orderPaymentMock.getOrderId()).isEqualTo(commandMock.getOrderId());
                assertThat(orderPaymentMock.getCardNumber()).isEqualTo(commandMock.getCardNumber());
                assertThat(orderPaymentMock.getCustomerId()).isEqualTo(commandMock.getCustomerId());
                assertThat(orderPaymentMock.getPrice()).isEqualTo(commandMock.getPrice());
                assertThat(orderPaymentMock.getOcurredOn()).isEqualTo(commandMock.getOcurredOn());
                assertThat(orderPaymentMock.getApprovalStatus()).isEqualTo(DENIED);

                verify(orderPaymentRepository, times(1)).getByOrderId(commandMock.getOrderId());
                verify(orderPaymentRepository, times(1)).save(orderPaymentMock);
                verify(orderPaymentMapper, times(0)).fromValidateOrderPaymentCommand(commandMock);
                verify(orderPaymentResultNotificationService, times(1)).notifyDenied(any());
                verify(orderPaymentResultNotificationService, times(0)).notifyApproved(any());

                verify(cardValidatorService, times(1)).isCreditCardValid(commandMock.getCardNumber());

            }
        }

        @Nested
        @DisplayName("when data has changed")
        class whenDataHasChanged{
            @Test
            @DisplayName("should approve payment when credit card is valid")
            public void shouldApprovePaymentWhenCreditCardIsValid(){

                orderPaymentMock.changeCardNumber("54321");
                orderPaymentMock.changePrice(300.0);

                when(orderPaymentRepository.getByOrderId(anyString())).thenReturn(Optional.of(orderPaymentMock));

                var commandMock = ValidateOrderPaymentCommandMock.mockWithInvalidCreditCard();
                validateOrderPaymentService.validate(commandMock);

                assertThat(orderPaymentMock.getOrderId()).isEqualTo(commandMock.getOrderId());
                assertThat(orderPaymentMock.getCardNumber()).isEqualTo(commandMock.getCardNumber());
                assertThat(orderPaymentMock.getCustomerId()).isEqualTo(commandMock.getCustomerId());
                assertThat(orderPaymentMock.getPrice()).isEqualTo(commandMock.getPrice());
                assertThat(orderPaymentMock.getOcurredOn()).isEqualTo(commandMock.getOcurredOn());
                assertThat(orderPaymentMock.getApprovalStatus()).isEqualTo(APPROVED);

                verify(orderPaymentRepository, times(1)).getByOrderId(commandMock.getOrderId());
                verify(orderPaymentRepository, times(1)).save(orderPaymentMock);
                verify(orderPaymentResultNotificationService, times(0)).notifyDenied(any());
                verify(orderPaymentResultNotificationService, times(1)).notifyApproved(any());
                verify(orderPaymentMapper, times(0)).fromValidateOrderPaymentCommand(commandMock);
                verify(cardValidatorService, times(1)).isCreditCardValid(commandMock.getCardNumber());

            }


            @Test
            @DisplayName("should deny payment when credit card is valid")
            public void shouldDenyPaymentWhenCreditCardIsValid(){

                orderPaymentMock.changeCardNumber("54321");
                orderPaymentMock.changePrice(300.0);

                when(cardValidatorService.isCreditCardValid(anyString())).thenReturn(false);

                when(orderPaymentRepository.getByOrderId(anyString())).thenReturn(Optional.of(orderPaymentMock));

                var commandMock = ValidateOrderPaymentCommandMock.mockWithInvalidCreditCard();
                validateOrderPaymentService.validate(commandMock);

                assertThat(orderPaymentMock.getOrderId()).isEqualTo(commandMock.getOrderId());
                assertThat(orderPaymentMock.getCardNumber()).isEqualTo(commandMock.getCardNumber());
                assertThat(orderPaymentMock.getCustomerId()).isEqualTo(commandMock.getCustomerId());
                assertThat(orderPaymentMock.getPrice()).isEqualTo(commandMock.getPrice());
                assertThat(orderPaymentMock.getOcurredOn()).isEqualTo(commandMock.getOcurredOn());
                assertThat(orderPaymentMock.getApprovalStatus()).isEqualTo(DENIED);

                verify(orderPaymentRepository, times(1)).getByOrderId(commandMock.getOrderId());
                verify(orderPaymentRepository, times(1)).save(orderPaymentMock);
                verify(orderPaymentResultNotificationService, times(1)).notifyDenied(any());
                verify(orderPaymentResultNotificationService, times(0)).notifyApproved(any());
                verify(orderPaymentMapper, times(0)).fromValidateOrderPaymentCommand(commandMock);
                verify(cardValidatorService, times(1)).isCreditCardValid(commandMock.getCardNumber());

            }
        }
    }



}
