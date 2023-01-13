package fr.giannesini.bank.account.kata.chatgpt;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Transaction {
    private final Amount amount;
    private final LocalDateTime timestamp;
    private final TransactionType type;

    public Transaction(Amount amount, TransactionType type) {
        this.amount = Objects.requireNonNull(amount);
        this.timestamp = LocalDateTime.now();
        this.type = Objects.requireNonNull(type);
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }
}


