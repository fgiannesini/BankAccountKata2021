package fr.giannesini.bank.account.kata;

import org.junit.jupiter.api.Disabled;
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

    @Disabled("Disabled during double loop implementation")
    @Test
    void should_get_historic() {
        var historic = new Account()
                .deposit(20, date(1))
                .withDraw(10, date(1))
                .historic(statements -> {
                    assertThat(statements).containsExactly(
                            statement(30, 20),
                            statement(14, -10)
                    );
                    return "result";
                });
        assertThat(historic).isEqualTo("result");
    }
}
