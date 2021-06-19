package fr.giannesini.bank.account.kata.account;

import fr.giannesini.bank.account.kata.historic.HistoricGenerator;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public record Account(List<AccountStatement> statements) {

    public Account() {
        this(List.of());
    }

    public Account deposit(MonetaryAmount deposit, LocalDate date) {
        return new Account(appendNewStatement(new AccountStatement(date, deposit)));
    }

    public Account withDraw(MonetaryAmount withdraw, LocalDate date) {
        return new Account(appendNewStatement(new AccountStatement(date, withdraw.negate())));
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
