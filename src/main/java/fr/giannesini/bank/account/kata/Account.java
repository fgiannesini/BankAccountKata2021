package fr.giannesini.bank.account.kata;

public record Account(double amount) {

    public Account() {
        this(0d);
    }

    public Account deposit(double deposit) {
        return new Account(deposit + amount);
    }

    public Account withDraw(double withdraw) {
        return new Account(amount - withdraw);
    }

    public String historic(HistoricGenerator historicGenerator) {
        return null;
    }
}
