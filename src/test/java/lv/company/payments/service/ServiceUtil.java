package lv.company.payments.service;

import lv.company.payments.model.Beneficiary;
import lv.company.payments.model.Payer;
import lv.company.payments.model.Payment;

import java.math.BigDecimal;

public class ServiceUtil {

    public static Payment createPayment() {
        Payer payer = new Payer("Valdis", "Skangalis", "skan@gmail.com");
        Beneficiary beneficiary = new Beneficiary("Rita", "Kļaviņa", "LVxxUNLA0987654321097");
        return new Payment(new BigDecimal("150.00"), "Child support", payer, beneficiary);
    }
}
