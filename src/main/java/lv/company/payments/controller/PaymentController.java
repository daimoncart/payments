package lv.company.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lv.company.payments.config.EmailConfig;
import lv.company.payments.model.Beneficiary;
import lv.company.payments.model.Payment;
import lv.company.payments.service.EmailService;
import lv.company.payments.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {

    private JwtUtil util;
    private EmailConfig emailConfig;
    private EmailService emailService;

    @PostMapping("/payments")
    @Operation(summary = "Send Payment", description = "Sends payment info to the authenticated user's email")
    public ResponseEntity<HttpStatus> postPayment(
            @Valid @RequestBody Payment payment
//            @RequestHeader("Authorization") String authHeader
    ) {
//        if (!isValidToken(authHeader)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
//        }

        emailService.sendSimpleEmailMessage("imb_lial@yahoo.com", null);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private boolean isValidToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }
        String token = authHeader.substring(7);
        return !util.isTokenExpired(token); // TODO - see if JWT filter with proper Spring Security configuration
    }
}
