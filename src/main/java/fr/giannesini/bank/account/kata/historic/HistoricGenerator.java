package fr.giannesini.bank.account.kata.historic;

import fr.giannesini.bank.account.kata.account.AccountStatement;

import java.util.List;

public interface HistoricGenerator {

    String generate(List<AccountStatement> statements);
}
