package lv.company.payments.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lv.company.payments.model.BankAccount;
import lv.company.payments.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final List<BankAccount> bankAccounts;

    @Override
    public boolean withdrawFunds(String email, BigDecimal amount) {
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
