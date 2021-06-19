package fr.giannesini.bank.account.kata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    void should_deposit_amount_twice() {
        var account = new Account();

        var newAccount = account
                .deposit(10d)
                .deposit(20d);

        assertThat(newAccount).isEqualTo(new Account(30d));
    }

    @Test
    void should_withdraw_amount_twice() {
        var account = new Account();

        var newAccount = account
                .withDraw(10d)
                .withDraw(20d);

        assertThat(newAccount).isEqualTo(new Account(-30d));
    }

    @Disabled("Disabled during double loop implementation")
    @Test
    void should_get_historic() {
        var historic = new Account()
                .deposit(20)
                .withDraw(10)
                .historic(statements -> {
                    assertThat(statements).containsExactly(
                            new AccountStatement(LocalDate.of(2021, Month.APRIL, 30), 20),
                            new AccountStatement(LocalDate.of(2021, Month.JUNE, 14), -10)
                    );
                    return "result";
                });
        assertThat(historic).isEqualTo("result");
    }
}
