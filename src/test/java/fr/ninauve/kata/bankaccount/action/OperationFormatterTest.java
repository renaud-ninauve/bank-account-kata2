package fr.ninauve.kata.bankaccount.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationFormatterTest {

    private static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LocalDateTime.of(2020, Month.SEPTEMBER, 12, 11, 43), ZoneId.of("Europe/Paris"));
    private static final String ACCOUNT_NUMBER = "111111";

    private OperationFormatter operationFormatter;

    @BeforeEach
    public void setUp() {

        this.operationFormatter = new OperationFormatter();
    }

    @Test
    public void should_format_deposit() {

        final String actual = operationFormatter.formatDeposit(ZONED_DATE_TIME, ACCOUNT_NUMBER, 1234, 5678);

        assertEquals("2020-09-12 11:43;111111;deposit;1234;5678", actual);
    }
}