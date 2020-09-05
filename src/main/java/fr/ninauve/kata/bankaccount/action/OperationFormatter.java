package fr.ninauve.kata.bankaccount.action;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OperationFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm");

    public enum OperationType {
        DEPOSIT, RETRIEVAL
    }

    public String format(ZonedDateTime dateTime, String accountNumber, OperationType operationType, long amountInCents, long balanceInCents) {

        final String dateTimeFormatted = dateTime.format(DATE_TIME_FORMATTER);
        return Stream.of(dateTimeFormatted, accountNumber, operationType.name().toLowerCase(), "" + amountInCents, "" + balanceInCents)
                .collect(Collectors.joining(";"));
    }
}
