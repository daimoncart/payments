package lv.company.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lv.company.payments.model.Payment;
import lv.company.payments.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final String EMAIL_SUBJECT = "Payment Transaction";

    @Override
    public void sendSimpleEmailMessage(String to, Payment payment) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EMAIL_SUBJECT);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText("This is working hah haha hahaa");
            emailSender.send(message);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailMessageWithAttachedPdf(String to, Payment payment) {
        // TO BE IMPLEMENTED ONE DAY
    }
}
