package lv.company.payments.service;

import lv.company.payments.model.BankAccount;
import lv.company.payments.service.impl.BankAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountServiceImplTest {

    private final String EMAIL = "test_user@inbox.lv";

    private ArrayList<BankAccount> bankAccounts;

    private BankAccountServiceImpl bankAccountService;

    @BeforeEach
    void setUp() {
        bankAccounts = new ArrayList<>();
        MockitoAnnotations.openMocks(this);
        bankAccountService = new BankAccountServiceImpl(bankAccounts);
    }

    @Test
    void withdrawFundsWithInsufficientBalance() {
        BigDecimal withdrawalAmount = new BigDecimal("11000");

        boolean result = bankAccountService.withdrawFunds(EMAIL, withdrawalAmount);

        assertFalse(result);
    }

    @Test
    void withdrawFundsWithSufficientAndThenInsufficientBalance() {
        BigDecimal withdrawalAmount1 = new BigDecimal("1000");
        BigDecimal withdrawalAmount2 = new BigDecimal("10000");

        boolean result1 = bankAccountService.withdrawFunds(EMAIL, withdrawalAmount1);
        boolean result2 = bankAccountService.withdrawFunds(EMAIL, withdrawalAmount2);

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void withdrawFundsTwiceWithSufficientBalance() {
        BigDecimal withdrawalAmount1 = new BigDecimal("5000");
        BigDecimal withdrawalAmount2 = new BigDecimal("5000");

        boolean result1 = bankAccountService.withdrawFunds(EMAIL, withdrawalAmount1);
        boolean result2 = bankAccountService.withdrawFunds(EMAIL, withdrawalAmount2);

        assertTrue(result1);
        assertTrue(result2);
    }
}
