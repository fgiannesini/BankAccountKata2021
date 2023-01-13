package fr.giannesini.bank.account.kata.chatgpt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BankAccount {
    private final Amount balance;
    private final List<Transaction> transactions;

    public BankAccount(Amount balance, List<Transaction> transactions) {
        this.balance = balance;
        this.transactions = Collections.unmodifiableList(transactions);
    }

    public BankAccount() {
        this(new Amount(0.0f), Collections.emptyList());
    }

    public BankAccount deposit(Amount amount) {
        return new BankAccount(new Amount(balance.getAmount() + amount.getAmount()), addTransaction(amount, TransactionType.DEPOSIT));
    }

    public BankAccount withdraw(Amount amount) {
        if (amount.getAmount() > this.balance.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new BankAccount(new Amount(balance.getAmount() - amount.getAmount()), addTransaction(amount, TransactionType.WITHDRAW));
    }

    public float getBalance() {
        return this.balance.getAmount();
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    private List<Transaction> addTransaction(Amount amount, TransactionType type) {
        List<Transaction> newTransactions = new ArrayList<>(transactions);
        newTransactions.add(new Transaction(amount, type));
        return newTransactions;
    }
}

