package fr.ninauve.kata.bankaccount.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static fr.ninauve.kata.bankaccount.domain.Operation.OperationType.DEPOSIT;
import static fr.ninauve.kata.bankaccount.domain.Operation.OperationType.RETRIEVAL;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LocalDateTime.of(2020, Month.SEPTEMBER, 12, 11, 43), ZoneId.of("Europe/Paris"));

    @Test
    public void balance_is_zero_when_no_operations() {

        final Account account = new Account();

        final long actual = account.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void fail_when_deposit_amount_is_negative() {

        final Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1, ZONED_DATE_TIME));
    }

    @Test
    public void fail_when_deposit_amount_is_zero() {

        final Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0, ZONED_DATE_TIME));
    }

    @Test
    public void balance_is_equal_to_deposit_when_one_deposit() {

        final Account account = new Account();
        account.deposit(4200, ZONED_DATE_TIME);

        final long actual = account.getBalance();

        assertEquals(4200, actual);
    }

    @Test
    public void fail_when_retrieval_amount_is_negative() {

        final Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.retrieve(-1, ZONED_DATE_TIME));
    }

    @Test
    public void fail_when_retrieval_amount_is_zero() {

        final Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.retrieve(0, ZONED_DATE_TIME));
    }

    @Test
    public void balance_is_equal_to_deposit_minus_retrieval() {

        final Account account = new Account();
        account.deposit(4200, ZONED_DATE_TIME);
        account.retrieve(1100, ZONED_DATE_TIME);

        final long actual = account.getBalance();

        assertEquals(3100, actual);
    }

    @Test
    public void history() {

        final List<Operation> expected = Arrays.asList(
                new Operation(DEPOSIT, ZONED_DATE_TIME, 4200, 4200),
                new Operation(RETRIEVAL, ZONED_DATE_TIME, 1100, 3100)
        );

        final Account account = new Account();
        account.deposit(4200, ZONED_DATE_TIME);
        account.retrieve(1100, ZONED_DATE_TIME);

        final List<Operation> actual = account.getHistoryOldestFirst();

        assertEquals(expected, actual);
    }

    @Test
    public void last_operation_when_not_empty() {

        final Operation expected = new Operation(RETRIEVAL, ZONED_DATE_TIME, 1100, 3100);

        final Account account = new Account();
        account.deposit(4200, ZONED_DATE_TIME);
        account.retrieve(1100, ZONED_DATE_TIME);

        final Operation actual = account.getLastOperationOrNull();

        assertEquals(expected, actual);
    }

    @Test
    public void last_operation_when_empty() {

        final Operation actual = new Account().getLastOperationOrNull();

        assertNull(actual);
    }
}