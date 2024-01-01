package lv.company.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lv.company.payments.config.EmailConfig;
import lv.company.payments.exception.PaymentProcessingException;
import lv.company.payments.model.Payment;
import lv.company.payments.security.UserPrincipal;
import lv.company.payments.service.EmailService;
import lv.company.payments.security.JwtProperties;
import lv.company.payments.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/payments")
    @Operation(summary = "Send Payment", description = "Sends payment info to the authenticated user's email")
    public ResponseEntity<HttpStatus> postPayment(
            @Valid @RequestBody Payment payment,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        logger.info("Entering payments controller");
        var email = removeQuotes(principal.getEmail());

        if (! email.equals(payment.payer().email())) {
            logger.error("Email address in the token different from the one in request body");
            throw new PaymentProcessingException(
                    "Email address in the token different from email address in the payload"
            );
        }

        service.checkTransactionAndSendMail(email, payment);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private String removeQuotes(String input) {
        if (input != null && input.length() > 1 && input.startsWith("\"") && input.endsWith("\"")) {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }

    @GetMapping("/test")
    public String testIt(@AuthenticationPrincipal UserPrincipal principal) {
        return "Hello this is working, " + principal.getEmail();
    } // TODO - remove this before final commit
}
