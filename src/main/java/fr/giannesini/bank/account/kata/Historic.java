package fr.giannesini.bank.account.kata;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Historic {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

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
        return statement.date().format(DATE_FORMATTER) + " " + DECIMAL_FORMATTER.format(statement.amount());
    }
}
