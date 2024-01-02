package lv.company.payments.service;

import lv.company.payments.config.EmailConfig;
import lv.company.payments.model.Payment;
import lv.company.payments.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static lv.company.payments.service.ServiceUtil.createPayment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailServiceImplTest {

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private EmailConfig config;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> messageCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(config.getUsername()).thenReturn("friendly.sender@bank.com");
    }

    @Test
    public void sendSimpleEmailMessageWhenPaymentSuccessful() {
        Payment payment = createPayment();
        emailService.sendSimpleEmailMessage("payer@gmail.com", payment, true);

        verify(emailSender).send(messageCaptor.capture());
        SimpleMailMessage sentMessage = messageCaptor.getValue();

        assertEquals("Payment Transaction (Success)", sentMessage.getSubject());
        assertEquals("friendly.sender@bank.com", sentMessage.getFrom());
        assertEquals("payer@gmail.com", sentMessage.getTo()[0]);
        assertTrue(sentMessage.getText().contains("successfully processed"));
    }

    @Test
    public void sendSimpleEmailMessageWhenPaymentUnsuccessful() {
        Payment payment = createPayment();
        emailService.sendSimpleEmailMessage("payer@gmail.com", payment, false);

        verify(emailSender).send(messageCaptor.capture());
        SimpleMailMessage sentMessage = messageCaptor.getValue();

        assertEquals("Payment Transaction (Failure)", sentMessage.getSubject());
        assertEquals("friendly.sender@bank.com", sentMessage.getFrom());
        assertEquals("payer@gmail.com", sentMessage.getTo()[0]);
        assertTrue(sentMessage.getText().contains("insufficient funds"));
    }
}