package fr.giannesini.bank.account.kata;

public record Account(double amount) {

    public Account() {
        this(0d);
    }

    public Account deposit(double deposit) {
        return new Account(deposit + amount);
    }
}
