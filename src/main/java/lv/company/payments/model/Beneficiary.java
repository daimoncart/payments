package lv.company.payments.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Details of the beneficiary receiving the payment")
public record Beneficiary(
        @Schema(description = "First name of the beneficiary")
        @Size(min = 2, message = "First name can not be shorter than two characters")
        String firstName,

        @Schema(description = "Last name of the beneficiary")
        @Size(min = 2, message = "Last name can not be shorter than two characters")
        String lastName,

        @Size(min = 21, max = 21, message = "Account number has to be 21 characters long")
        @Schema(description = "Account number (IBAN) of the beneficiary",
                example = "LVxxPARX1234567890123")
        String accountNo
) {}
