package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.MenuTestConstants;
import fr.ninauve.kata.bankaccount.MessageTestConstants;
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
    private Console console;
    @Mock
    private Session session;
    @Mock
    private ReadAccountNumberAction readAccountNumberAction;

    @BeforeEach
    public void setUp() {

        this.menuAction = new MenuAction(console, session, readAccountNumberAction);
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
    }

    @Test
    public void should_read_account_number_deposit() {

        when(console.waitAndGetInput())
                .thenReturn(MenuTestConstants.VALUE_DEPOSIT);

        final Action actual = menuAction.execute();
        assertSame(readAccountNumberAction, actual);

        final InOrder inOrder = inOrder(session);
        inOrder.verify(session).clear();
        inOrder.verify(session).setMenuItem(MenuItem.DEPOSIT);
    }
}