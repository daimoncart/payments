package lv.company.payments.service;

import lv.company.payments.model.Payment;

public interface PaymentService {
    void checkTransactionAndSendMail(String email, Payment payment);
}
