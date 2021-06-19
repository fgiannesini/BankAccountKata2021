package fr.giannesini.bank.account.kata;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    void should_deposit_amount_twice() {
        var account = new Account();

        var newAccount = account
                .deposit(10, date(1))
                .deposit(20d, date(2));

        assertThat(newAccount).isEqualTo(new Account(List.of(
                statement(1, 10),
                statement(2, 20)
        )));
    }

    @Test
    void should_withdraw_amount_twice() {
        var account = new Account();

        var newAccount = account
                .withDraw(10, date(1))
                .withDraw(20d, date(2));

        assertThat(newAccount).isEqualTo(new Account(List.of(
                statement(1, -10),
                statement(2, -20)
        )));
    }

    private LocalDate date(int day) {
        return LocalDate.of(2021, Month.APRIL, day);
    }

    private AccountStatement statement(int day, double amount) {
        return new AccountStatement(date(day), amount);
    }

    @Test
    void should_generate_historic() {
        var historic = new Account()
                .deposit(20, date(14))
                .withDraw(10, date(30))
                .historic(statements -> {
                    assertThat(statements).containsExactly(
                            statement(14, 20),
                            statement(30, -10)
                    );
                    return "Historic called";
                });
        assertThat(historic).isEqualTo("Historic called");
    }
}
