package fr.giannesini.bank.account.kata.account;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

public record AccountStatement(LocalDate date, MonetaryAmount monetaryAmount) {
}
