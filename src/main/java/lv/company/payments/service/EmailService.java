package lv.company.payments.service;

import lv.company.payments.model.Payment;

public interface EmailService {
    void sendSimpleEmailMessage(String to, Payment payment, boolean wasPaymentSuccessful);
    void sendEmailMessageWithAttachedPdf(String to, Payment payment, boolean wasPaymentSuccessful);
}
