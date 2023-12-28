package lv.company.payments.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Credentials of the login user")
public record LoginRequest(
        @Email(message = "Email format is not valid")
        @NotBlank(message = "Email address can not be empty")
        @Schema(description = "Email address used for login and sending payment info",
                example = "user@example.com")
        String username,

        @Schema(description = "User's password", example = "password123")
        @NotBlank(message = "Password can not be empty")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {}
