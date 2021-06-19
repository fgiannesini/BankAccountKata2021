package fr.giannesini.bank.account.kata;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public record Account(List<AccountStatement> statements) {

    public Account() {
        this(List.of());
    }

    public Account deposit(double deposit, LocalDate date) {
        return new Account(appendNewStatement(new AccountStatement(date, deposit)));
    }

    public Account withDraw(double withdraw, LocalDate date) {
        return new Account(appendNewStatement(new AccountStatement(date, -withdraw)));
    }

    private List<AccountStatement> appendNewStatement(AccountStatement accountStatement) {
        return Stream.concat(
                statements.stream(),
                Stream.of(accountStatement))
                .toList();
    }

    public String historic(HistoricGenerator historicGenerator) {
        return historicGenerator.generate(statements);
    }
}
