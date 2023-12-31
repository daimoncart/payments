package lv.company.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lv.company.payments.config.EmailConfig;
import lv.company.payments.model.Beneficiary;
import lv.company.payments.model.Payment;
import lv.company.payments.security.UserPrincipal;
import lv.company.payments.service.EmailService;
import lv.company.payments.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private JwtUtil util;
    private EmailConfig emailConfig;
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/payments")
    @Operation(summary = "Send Payment", description = "Sends payment info to the authenticated user's email")
    public ResponseEntity<HttpStatus> postPayment(
            @Valid @RequestBody Payment payment,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        // emailService.sendSimpleEmailMessage("imb_lial@yahoo.com", null);
        logger.info("Entering payments controller");

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testIt(@AuthenticationPrincipal UserPrincipal principal) {
        return "Hello this is working, " + principal.getEmail();
    }
}
