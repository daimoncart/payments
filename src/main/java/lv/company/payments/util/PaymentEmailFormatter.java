package lv.company.payments.util;

import lv.company.payments.model.Payment;

public class PaymentEmailFormatter {
    private static final String SUCCESS_TEMPLATE =
            "Dear %s %s,\n\n" +
                    "We are pleased to inform you that your payment request of %s EUR to %s %s's account %s has been successfully processed.\n\n" +
                    "Best regards,\n" +
                    "Your Friendly Bank";

    private static final String FAILURE_TEMPLATE =
            "Dear %s %s,\n\n" +
                    "We regret to inform you that your payment request of %s EUR to %s %s's account %s could not be processed due to insufficient funds.\n\n" +
                    "Best regards,\n" +
                    "Your Friendly Bank";

    public static String formatPaymentEmail(boolean wasPaymentSuccessful, Payment payment) {
        String template = wasPaymentSuccessful ? SUCCESS_TEMPLATE : FAILURE_TEMPLATE;
        return String.format(
                template,
                payment.payer().firstName(),
                payment.payer().lastName(),
                payment.amount(),
                payment.beneficiary().firstName(),
                payment.beneficiary().lastName(),
                payment.beneficiary().accountNo()
        );
    }
}
