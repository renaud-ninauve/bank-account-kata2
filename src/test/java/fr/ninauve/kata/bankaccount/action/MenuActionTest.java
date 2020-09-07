package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.MenuTestConstants;
import fr.ninauve.kata.bankaccount.MessageTestConstants;
import fr.ninauve.kata.bankaccount.Scenario;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuActionTest {

    private MenuAction menuAction;

    @Mock
    private Scenario scenario;
    @Mock
    private Action nextAction;
    @Mock
    private Console console;
    @Mock
    private Session session;

    @BeforeEach
    public void setUp() {

        this.menuAction = new MenuAction(scenario, console, session);
    }

    @Test
    public void should_validate() {

        when(console.waitAndGetInput())
                .thenReturn("BAD");

        final Action actual = menuAction.execute();

        assertSame(menuAction, actual);
        verify(console).printLines(singletonList(MessageTestConstants.BAD_INPUT_MENU));
    }

    @Test
    public void should_exit() {

        when(console.waitAndGetInput())
                .thenReturn(MenuTestConstants.VALUE_EXIT);

        final Action actual = menuAction.execute();

        assertNull(actual);
        final InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLines(MessageTestConstants.MENU);
        inOrder.verify(console).waitAndGetInput();
        inOrder.verifyNoMoreInteractions();
        verifyZeroInteractions(scenario);
    }

    @Test
    public void should_read_account_number_deposit() {

        when(scenario.pollNextAction())
                .thenReturn(nextAction);
        when(console.waitAndGetInput())
                .thenReturn(MenuTestConstants.VALUE_DEPOSIT);

        final Action actual = menuAction.execute();

        assertSame(nextAction, actual);
        verify(scenario).pollNextAction();

        final InOrder inOrder = inOrder(session);
        inOrder.verify(session).clear();
        inOrder.verify(session).setMenuItem(MenuItem.DEPOSIT);

        verify(scenario).initDepositOrRetrieval();
    }

    @Test
    public void should_read_account_number_retrieval() {

        when(scenario.pollNextAction())
                .thenReturn(nextAction);
        when(console.waitAndGetInput())
                .thenReturn(MenuTestConstants.VALUE_RETRIEVAL);

        final Action actual = menuAction.execute();
        assertSame(nextAction, actual);

        final InOrder inOrder = inOrder(session);
        inOrder.verify(session).clear();
        inOrder.verify(session).setMenuItem(MenuItem.RETRIEVAL);

        verify(scenario).initDepositOrRetrieval();
    }

    @Test
    public void should_navigate_to_history() {

        when(scenario.pollNextAction())
                .thenReturn(nextAction);
        when(console.waitAndGetInput())
                .thenReturn(MenuTestConstants.VALUE_HISTORY);

        final Action actual = menuAction.execute();
        assertSame(nextAction, actual);

        final InOrder inOrder = inOrder(session);
        inOrder.verify(session).clear();
        inOrder.verify(session).setMenuItem(MenuItem.HISTORY);

        verify(scenario).initHistory();
    }
}