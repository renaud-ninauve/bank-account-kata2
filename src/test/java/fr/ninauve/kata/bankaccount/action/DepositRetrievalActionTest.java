package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.domain.Account;
import fr.ninauve.kata.bankaccount.domain.AccountRepository;
import fr.ninauve.kata.bankaccount.domain.Operation;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositRetrievalActionTest {

    private static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LocalDateTime.of(2020, Month.SEPTEMBER, 12, 11, 43), ZoneId.of("Europe/Paris"));
    private static final Clock CLOCK = Clock.fixed(ZONED_DATE_TIME.toInstant(), ZONED_DATE_TIME.getZone());
    private static final String ACCOUNT_NUMBER = "111111";
    private static final long AMOUNT = 4200;
    private static final long BALANCE = 6000;
    private static final String FORMATTED_OPERATION = "FORMATTED_DEPOSIT";

    private DepositRetrievalAction depositRetrievalAction;

    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private Account account;
    @Mock
    private MenuAction menuAction;
    @Mock
    private OperationFormatter operationFormatter;

    @BeforeEach
    public void setUp() {

        this.depositRetrievalAction = new DepositRetrievalAction(console, session, CLOCK, accountRepository, menuAction, operationFormatter);
    }

    @Test
    public void should_print_deposit() {

        final Operation operation = new Operation(
                Operation.OperationType.DEPOSIT, ZONED_DATE_TIME, AMOUNT, BALANCE);
        when(session.getMenuItem())
                .thenReturn(MenuItem.DEPOSIT);
        when(session.getAccountNumber())
                .thenReturn(ACCOUNT_NUMBER);
        when(session.getAmount())
                .thenReturn(AMOUNT);
        when(accountRepository.getOrCreate(ACCOUNT_NUMBER))
                .thenReturn(account);
        when(account.getLastOperationOrNull())
                .thenReturn(operation);
        when(operationFormatter.format(
                ACCOUNT_NUMBER, operation
        )).thenReturn(FORMATTED_OPERATION);

        final Action actual = depositRetrievalAction.execute();

        assertSame(menuAction, actual);
        verify(account).deposit(AMOUNT, ZONED_DATE_TIME);
        verify(console).printLines(singletonList(FORMATTED_OPERATION));
    }

    @Test
    public void should_print_retrieval() {

        final Operation operation = new Operation(
                Operation.OperationType.RETRIEVAL, ZONED_DATE_TIME, AMOUNT, BALANCE);
        when(session.getMenuItem())
                .thenReturn(MenuItem.RETRIEVAL);
        when(session.getAccountNumber())
                .thenReturn(ACCOUNT_NUMBER);
        when(session.getAmount())
                .thenReturn(AMOUNT);
        when(accountRepository.getOrCreate(ACCOUNT_NUMBER))
                .thenReturn(account);
        when(account.getLastOperationOrNull())
                .thenReturn(operation);
        when(operationFormatter.format(
                ACCOUNT_NUMBER, operation
        )).thenReturn(FORMATTED_OPERATION);

        final Action actual = depositRetrievalAction.execute();

        assertSame(menuAction, actual);
        verify(account).retrieval(AMOUNT, ZONED_DATE_TIME);
        verify(console).printLines(singletonList(FORMATTED_OPERATION));
    }
}