package lv.company.payments.service;

import java.math.BigDecimal;

public interface BankAccountService {
    boolean withdrawFunds(String username, BigDecimal amount);
}
