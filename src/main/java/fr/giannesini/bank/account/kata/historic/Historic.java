package fr.giannesini.bank.account.kata.historic;

import fr.giannesini.bank.account.kata.account.AccountStatement;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Historic implements HistoricGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final MonetaryAmountFormat MONEY_FORMATTER = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(Locale.getDefault()).build());

    @Override
    public String generate(List<AccountStatement> statements) {
        String historicLines = statements.stream()
                .map(this::historicLine)
                .collect(Collectors.joining("\n"));
        return String.format("""
                Date Amount
                %s
                """, historicLines);
    }

    private String historicLine(AccountStatement statement) {
        return String.join(" ", statement.date().format(DATE_FORMATTER), MONEY_FORMATTER.format(statement.monetaryAmount()));
    }
}
