package fr.giannesini.bank.account.kata.chatgpt;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {
    @Test
    void testDeposit() {
        BankAccount account = new BankAccount();
        Amount amount = new Amount(100.0f);
        BankAccount newAccount = account.deposit(amount);
        assertEquals(amount.getAmount(), newAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount(new Amount(100.0f), List.of());
        Amount amount = new Amount(50.0f);
        BankAccount newAccount = account.withdraw(amount);
        assertEquals(amount.getAmount(), newAccount.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount(new Amount(100.0f), List.of());
        Amount amount = new Amount(150.0f);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
    }

    @Test
    void testGetTransactions() {
        BankAccount account = new BankAccount(new Amount(100.0f), List.of());
        Amount amount = new Amount(50.0f);
        BankAccount newAccount = account.withdraw(amount);
        List<Transaction> transactions = newAccount.getTransactions();
        assertEquals(1, transactions.size());
        assertEquals(TransactionType.WITHDRAW, transactions.get(0).getType());
    }
}
