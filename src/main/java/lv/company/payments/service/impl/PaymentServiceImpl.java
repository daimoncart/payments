package lv.company.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lv.company.payments.model.Payment;
import lv.company.payments.service.BankAccountService;
import lv.company.payments.service.EmailService;
import lv.company.payments.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final EmailService emailService;
    private final BankAccountService bankAccountService;

    @Override
    public void checkTransactionAndSendMail(String email, Payment payment) {
        boolean wasTransactionSuccessful = bankAccountService.withdrawFunds(email, payment.amount());
        emailService.sendSimpleEmailMessage(email, payment, wasTransactionSuccessful);
    }
}
