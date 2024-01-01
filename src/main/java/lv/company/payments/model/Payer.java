package lv.company.payments.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Information about the payer of the transaction")
public record Payer(
        @Schema(description = "First name of the payer")
        @Size(min = 2, message = "First name can not be shorter than two characters")
        @NotNull
        String firstName,

        @Schema(description = "Last name of the payer")
        @Size(min = 2, message = "Last name can not be shorter than two characters")
        @NotNull
        String lastName,

        @Email(message = "Email format is not valid")
        @NotBlank(message = "Email address can not be empty")
        @Schema(description = "Email address used for sending payment info and login",
                example = "user@example.com")
        @NotNull
        String email
) {}
