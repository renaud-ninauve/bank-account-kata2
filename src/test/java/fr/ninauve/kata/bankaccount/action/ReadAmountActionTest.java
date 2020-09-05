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
class ReadAmountActionTest {

    private static final String INPUT_AMOUNT = "4200";
    private static final long AMOUNT = 4200;

    private ReadAmountAction readAmountAction;

    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private DepositRetrievalAction depositRetrievalAction;
    @Mock
    private AmountInputValidator amountInputValidator;

    @BeforeEach
    public void setUp() {

        this.readAmountAction = new ReadAmountAction(console, session, depositRetrievalAction, amountInputValidator);
    }

    @Test
    public void should_read_amount_then_deposit() {

        when(console.waitAndGetInput())
                .thenReturn(INPUT_AMOUNT);
        when(amountInputValidator.isValid(INPUT_AMOUNT))
                .thenReturn(true);

        final Action actual = readAmountAction.execute();

        assertSame(depositRetrievalAction, actual);
        verify(console).printLines(singletonList(MessageTestConstants.WHAT_AMOUNT));
        verify(session).setAmount(AMOUNT);
        verifyNoMoreInteractions(session);
    }

    @Test
    public void should_validate() {

        when(console.waitAndGetInput())
                .thenReturn("BAD");
        when(amountInputValidator.isValid("BAD"))
                .thenReturn(false);

        final Action actual = readAmountAction.execute();

        assertSame(readAmountAction, actual);
        verify(console).printLines(singletonList(MessageTestConstants.WHAT_AMOUNT));
        verify(console).printLines(singletonList(MessageTestConstants.BAD_PARAM_AMOUNT));
        verifyNoMoreInteractions(session);
    }
}