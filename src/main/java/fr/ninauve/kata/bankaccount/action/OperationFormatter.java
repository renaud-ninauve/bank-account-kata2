package fr.ninauve.kata.bankaccount.action;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OperationFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm");
    private static final String OPERATION_DEPOSIT = "deposit";

    public String formatDeposit(ZonedDateTime dateTime, String accountNumber, long amountInCents, long balanceInCents) {

        final String dateTimeFormatted = dateTime.format(DATE_TIME_FORMATTER);
        return Stream.of(dateTimeFormatted, accountNumber, OPERATION_DEPOSIT, "" + amountInCents, "" + balanceInCents)
                .collect(Collectors.joining(";"));
    }
}
