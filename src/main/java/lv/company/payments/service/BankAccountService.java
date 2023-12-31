package lv.company.payments.service;

import lv.company.payments.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {
    boolean withdrawFunds(String username, BigDecimal amount);
}
