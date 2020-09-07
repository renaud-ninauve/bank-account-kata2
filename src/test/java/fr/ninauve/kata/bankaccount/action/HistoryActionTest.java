package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Scenario;
import fr.ninauve.kata.bankaccount.domain.Account;
import fr.ninauve.kata.bankaccount.domain.AccountRepository;
import fr.ninauve.kata.bankaccount.domain.Operation;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryActionTest {

    private static final String ACCOUNT_NUMBER = "111111";

    public static final Operation OPERATION1 = new Operation(null, null, 1, 0);
    public static final Operation OPERATION2 = new Operation(null, null, 2, 0);
    public static final Operation OPERATION3 = new Operation(null, null, 3, 0);
    private static final List<Operation> HISTORY = Arrays.asList(
            OPERATION1,
            OPERATION2,
            OPERATION3);

    private static final List<String> FORMATTED_OPERATIONS = Arrays.asList(
            "OP-1", "OP-2", "OP-3"
    );

    private HistoryAction historyAction;

    @Mock
    private Scenario scenario;
    @Mock
    private Action nextAction;
    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OperationFormatter operationFormatter;
    @Mock
    private Account account;

    @BeforeEach
    public void setUp() {

        when(scenario.pollNextAction())
                .thenReturn(nextAction);

        this.historyAction = new HistoryAction(scenario, console, session, accountRepository, operationFormatter);
    }

    @Test
    public void should_print_history_then_return_menu() {

        when(session.getAccountNumber())
                .thenReturn(ACCOUNT_NUMBER);
        when(accountRepository.getOrCreate(ACCOUNT_NUMBER))
                .thenReturn(account);
        when(account.getHistoryOldestFirst())
                .thenReturn(HISTORY);

        when(operationFormatter.format(eq(ACCOUNT_NUMBER), any()))
                .thenReturn(
                        FORMATTED_OPERATIONS.get(0),
                        FORMATTED_OPERATIONS.get(1),
                        FORMATTED_OPERATIONS.get(2));

        final Action actual = historyAction.execute();

        assertSame(nextAction, actual);
        verify(scenario).pollNextAction();

        verify(console).printLines(FORMATTED_OPERATIONS);
    }
}