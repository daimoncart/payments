package lv.company.payments.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccount {
    private final String email;
    private BigDecimal balance = BigDecimal.valueOf(10_000);
}
