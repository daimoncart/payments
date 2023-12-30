package lv.company.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lv.company.payments.model.AuthRequest;
import lv.company.payments.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/authenticate")
    @Operation(summary = "Login user", description = "Logs a user in and returns a token")
    @ApiResponse(responseCode = "200", description = "Successful login",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(implementation = String.class)))
    public String loginUser(@Valid @RequestBody AuthRequest request) { // TODO - work needed on error message for the wrong input

        String token = jwtUtil.createJwtToken(request.username());
        logger.info("Token generated for user: {}, expires in: {} minutes",
                request.username(),
                jwtUtil.getExpiryMinutes());

        return token;
    }
}