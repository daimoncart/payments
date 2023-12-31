package lv.company.payments.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Representation of a payment transaction")
public record Payment(
        @Schema(description = "Amount of the payment transaction")
        @NotNull
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
        @Digits(integer = Integer.MAX_VALUE, fraction = 2,
                message = "Amount must not have more than two decimal places")
        BigDecimal amount,

        @Schema(description = "Payment transaction description")
        String description,

        @Schema(description = "Details of a payer")
        @NotNull
        @Valid
        Payer payer,

        @Schema(description = "Details of a beneficiary")
        @NotNull
        @Valid
        Beneficiary beneficiary
) {}
