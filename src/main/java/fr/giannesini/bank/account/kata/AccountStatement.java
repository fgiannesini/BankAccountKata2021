package fr.giannesini.bank.account.kata;

import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.Locale;

public record AccountStatement(LocalDate date, MonetaryAmount monetaryAmount, double amount) {

    public AccountStatement(LocalDate date, double amount) {
        this(date, Money.of(amount, Monetary.getCurrency(Locale.getDefault())), amount);
    }

    public AccountStatement(LocalDate date, MonetaryAmount amount) {
        this(date, amount, amount.getNumber().doubleValueExact());
    }
}
