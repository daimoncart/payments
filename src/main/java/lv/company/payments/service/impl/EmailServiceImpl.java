package lv.company.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lv.company.payments.config.EmailConfig;
import lv.company.payments.model.Payment;
import lv.company.payments.service.EmailService;
import lv.company.payments.util.PaymentEmailFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final EmailConfig config;

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final String EMAIL_SUBJECT_SUCCESS = "Payment Transaction (Success)";
    private final String EMAIL_SUBJECT_FAILURE = "Payment Transaction (Failure)";

    @Override
    public void sendSimpleEmailMessage(String to, Payment payment, boolean wasPaymentSuccessful) {
        var emailText = PaymentEmailFormatter.formatPaymentEmail(wasPaymentSuccessful, payment);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(wasPaymentSuccessful ? EMAIL_SUBJECT_SUCCESS : EMAIL_SUBJECT_FAILURE);
            message.setFrom(config.getUsername());
            message.setTo(to);
            message.setText(emailText);
            emailSender.send(message);
            logger.info("Email with payment information sent.");

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailMessageWithAttachedPdf(String to, Payment payment, boolean wasPaymentSuccessful) {
        // TO BE IMPLEMENTED ONE DAY. PROBABLY NOT.
    }
}
