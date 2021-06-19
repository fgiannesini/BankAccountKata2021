package fr.giannesini.bank.account.kata;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class HistoricTest {

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.FRANCE);
    }

    @Test
    void generate_historic() {
        var historic = new Historic();
        List<AccountStatement> statements = List.of(
                new AccountStatement(LocalDate.of(2021, Month.MAY, 1), money(50)),
                new AccountStatement(LocalDate.of(2021, Month.MAY, 2), money(-25))
        );
        var result = historic.generate(statements);

        assertThat(result).isEqualTo("""
                Date Amount
                01/05/2021 50,00 EUR
                02/05/2021 -25,00 EUR
                """);
    }

    private Money money(int amount) {
        return Money.of(amount, Monetary.getCurrency(Locale.getDefault()));
    }
}