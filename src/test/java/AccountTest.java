import fr.giannesini.bank.account.kata.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    void should_deposit_amount_twice() {
        var account = new Account();

        Account newAccount = account
                .deposit(10d)
                .deposit(20d);

        assertThat(newAccount).isEqualTo(new Account(30d));
    }
}
