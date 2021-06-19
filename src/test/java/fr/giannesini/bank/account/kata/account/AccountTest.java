package fr.giannesini.bank.account.kata.account;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    void should_deposit_amount_twice() {
        var account = new Account();

        var newAccount = account
                .deposit(money(10), date(1))
                .deposit(money(20), date(2));

        assertThat(newAccount).isEqualTo(new Account(List.of(
                statement(1, money(10)),
                statement(2, money(20))
        )));
    }

    @Test
    void should_withdraw_amount_twice() {
        var account = new Account();

        var newAccount = account
                .withDraw(money(10), date(1))
                .withDraw(money(20), date(2));

        assertThat(newAccount).isEqualTo(new Account(List.of(
                statement(1, money(-10)),
                statement(2, money(-20))
        )));
    }

    private Money money(int amount) {
        return Money.of(amount, Monetary.getCurrency(Locale.getDefault()));
    }

    private LocalDate date(int day) {
        return LocalDate.of(2021, Month.APRIL, day);
    }

    private AccountStatement statement(int day, MonetaryAmount amount) {
        return new AccountStatement(date(day), amount);
    }

    @Test
    void should_generate_historic() {
        var historic = new Account()
                .deposit(money(20), date(14))
                .withDraw(money(10), date(30))
                .historic(statements -> {
                    assertThat(statements).containsExactly(
                            statement(14, money(20)),
                            statement(30, money(-10))
                    );
                    return "Historic called";
                });
        assertThat(historic).isEqualTo("Historic called");
    }
}
