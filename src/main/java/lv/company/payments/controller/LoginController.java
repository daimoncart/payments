package lv.company.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lv.company.payments.model.LoginRequest;
import lv.company.payments.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoginController {

    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Logs a user in and returns a token")
    @ApiResponse(responseCode = "200", description = "Successful login",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(implementation = String.class)))
    public String loginUser(@Valid @RequestBody LoginRequest request) {

        String token = jwtUtil.createJwtToken(request.username());
        logger.info("Token generated for user: {}, expires in: {} minutes",
                request.username(),
                jwtUtil.getExpiryMinutes()); // TODO - replace 30 with the actual token expiration time

        return token;
    }
}
