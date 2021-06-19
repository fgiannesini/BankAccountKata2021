package fr.giannesini.bank.account.kata;

import java.time.LocalDate;

public record AccountStatement(LocalDate date, double amount) {
}
