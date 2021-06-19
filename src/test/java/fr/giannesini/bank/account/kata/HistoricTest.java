package fr.giannesini.bank.account.kata;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HistoricTest {

    @Test
    void generate_historic() {
        var historic = new Historic();
        List<AccountStatement> statements = List.of(
                new AccountStatement(LocalDate.of(2021, Month.MAY, 1), 50),
                new AccountStatement(LocalDate.of(2021, Month.MAY, 2), -25)
        );
        var result = historic.generate(statements);

        assertThat(result).isEqualTo("""
                Date Amount
                01/05/2021 50.00
                02/05/2021 -25.00
                """);
    }
}