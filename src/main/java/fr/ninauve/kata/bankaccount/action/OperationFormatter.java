package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.domain.Operation;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OperationFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm");

    public String format(String accountNumber, Operation operation) {

        final String dateTimeFormatted = operation.getDateTime().format(DATE_TIME_FORMATTER);
        return Stream.of(
                dateTimeFormatted,
                accountNumber,
                operation.getOperationType().name().toLowerCase(),
                "" + operation.getAmount(),
                "" + operation.getBalance()
        ).collect(Collectors.joining(";"));
    }
}
