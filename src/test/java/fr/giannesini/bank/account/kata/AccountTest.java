package fr.giannesini.bank.account.kata;

import org.junit.jupiter.api.Test;

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
}
