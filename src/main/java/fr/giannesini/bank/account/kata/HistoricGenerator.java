package fr.giannesini.bank.account.kata;

import java.util.List;

public interface HistoricGenerator {

    String generate(List<AccountStatement> statements);
}
