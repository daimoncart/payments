package lv.company.payments.service;

import lv.company.payments.model.Payment;
import lv.company.payments.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentServiceImplTest {

    @Mock
    private EmailService emailService;

    @Mock
    private BankAccountService bankAccountService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckTransactionAndSendMail_SuccessfulTransaction() {
        String email = "payer@gmail.com";
        Payment payment = ServiceUtil.createPayment();

        when(bankAccountService.withdrawFunds(email, payment.amount())).thenReturn(true);

        paymentService.checkTransactionAndSendMail(email, payment);

        verify(bankAccountService).withdrawFunds(email, payment.amount());
        verify(emailService).sendSimpleEmailMessage(email, payment, true);
    }

    @Test
    public void testCheckTransactionAndSendMail_UnsuccessfulTransaction() {
        String email = "payer@gmail.com";
        Payment payment = ServiceUtil.createPayment();

        when(bankAccountService.withdrawFunds(email, payment.amount())).thenReturn(false);

        paymentService.checkTransactionAndSendMail(email, payment);

        verify(bankAccountService).withdrawFunds(email, payment.amount());
        verify(emailService).sendSimpleEmailMessage(email, payment, false);
    }
}
