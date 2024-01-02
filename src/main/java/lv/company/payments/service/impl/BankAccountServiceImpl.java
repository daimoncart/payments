package lv.company.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lv.company.payments.controller.AuthenticationController;
import lv.company.payments.model.BankAccount;
import lv.company.payments.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final List<BankAccount> bankAccounts;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public boolean withdrawFunds(String email, BigDecimal amount) {
        logger.info("Checking if withdrawal can be made");
        BankAccount account = findOrCreateAccount(email);
        return processWithdrawal(account, amount);
    }

    private BankAccount findOrCreateAccount(String email) {
        return bankAccounts.stream()
                .filter(b -> b.getEmail().equals(email))
                .findFirst()
                .orElseGet(() -> createNewAccount(email));
    }

    private BankAccount createNewAccount(String email) {
        BankAccount newAccount = new BankAccount(email);
        bankAccounts.add(newAccount);
        return newAccount;
    }

    private boolean processWithdrawal(BankAccount account, BigDecimal amount) {
        if (isWithdrawalPossible(account, amount)) {
            account.setBalance(account.getBalance().subtract(amount));
            return true;
        }
        return false;
    }

    private boolean isWithdrawalPossible(BankAccount account, BigDecimal amount) {
        return account.getBalance().compareTo(amount) >= 0;
    }
}
