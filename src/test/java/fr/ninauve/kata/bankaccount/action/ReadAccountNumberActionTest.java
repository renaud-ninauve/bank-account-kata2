package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.MessageTestConstants;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReadAccountNumberActionTest {

    private static final String ACCOUNT_NUMBER = "12345";

    private ReadAccountNumberAction readAccountNumberAction;

    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private ReadAmountAction readAmountAction;

    @BeforeEach
    public void setUp() {

        this.readAccountNumberAction = new ReadAccountNumberAction(console, session, readAmountAction);
    }

    @Test
    public void should_read_account_number_then_amount() {

        when(console.waitAndGetInput())
                .thenReturn(ACCOUNT_NUMBER);

        final Action actual = readAccountNumberAction.execute();

        assertSame(readAmountAction, actual);
        verify(console).printLines(singletonList(MessageTestConstants.WHAT_ACCOUNT_NUMBER));
        verify(session).setAccountNumber(ACCOUNT_NUMBER);
        verifyNoMoreInteractions(session);
    }
}