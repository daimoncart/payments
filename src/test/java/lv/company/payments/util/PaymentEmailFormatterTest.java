package lv.company.payments.util;

import lv.company.payments.model.Beneficiary;
import lv.company.payments.model.Payer;
import lv.company.payments.model.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentEmailFormatterTest {

    @Test
    public void testFormatPaymentEmailWhenSuccessful() {
        Payer payer = new Payer("Henrihs", "Apse", "user@example.com");
        Beneficiary beneficiary = new Beneficiary("Dana", "Apse", "LVxxPARX1234567890123");
        Payment payment = new Payment(new BigDecimal("100.00"), "Rent", payer, beneficiary);

        String email = PaymentEmailFormatter.formatPaymentEmail(true, payment);
        String expectedEmail =
                "Dear Henrihs Apse,\n\n" +
                        "We are pleased to inform you that your payment request of 100.00 EUR to Dana Apse's account LVxxPARX1234567890123 has been successfully processed.\n\n" +
                        "Best regards,\n" +
                        "Your Friendly Bank";

        assertEquals(expectedEmail, email);
    }

    @Test
    public void testFormatPaymentEmailWhenUnsuccessful() {
        Payer payer = new Payer("Henrihs", "Apse", "user@example.com");
        Beneficiary beneficiary = new Beneficiary("Šarlote", "Knaģe", "LVxxPARX1234567890123");
        Payment payment = new Payment(new BigDecimal("500.00"), "Rent", payer, beneficiary);

        String email = PaymentEmailFormatter.formatPaymentEmail(false, payment);
        String expectedEmail =
                "Dear Henrihs Apse,\n\n" +
                        "We regret to inform you that your payment request of 500.00 EUR to Šarlote Knaģe's account LVxxPARX1234567890123 could not be processed due to insufficient funds.\n\n" +
                        "Best regards,\n" +
                        "Your Friendly Bank";

        assertEquals(expectedEmail, email);
    }
}
