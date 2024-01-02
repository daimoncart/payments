package lv.company.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lv.company.payments.model.AuthRequest;
import lv.company.payments.model.AuthResponse;
import lv.company.payments.security.JwtIssuer;
import lv.company.payments.security.JwtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtIssuer jwtIssuer;
    private final JwtProperties jwtProperties;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/authenticate")
    @Operation(summary = "Login user", description = "Logs a user in and returns a token")
    @ApiResponse(responseCode = "200", description = "Successful login",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = AuthResponse.class)))
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest request) {

        logger.info("Entering authentication controller");

        var token = jwtIssuer.issue(1L, request.username(), List.of("USER"));
        logger.info("Token generated for user: {}, expires in: {} minutes",
                request.username(),
                jwtProperties.getExpiryMinutes());

        var response = AuthResponse.builder()
                .authToken(token)
                .build();

        logger.info("Exiting authentication controller");

        return ResponseEntity.ok(response);
    }
}
