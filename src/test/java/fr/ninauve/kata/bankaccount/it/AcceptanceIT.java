package fr.ninauve.kata.bankaccount.it;

import fr.ninauve.kata.bankaccount.Main;
import fr.ninauve.kata.bankaccount.MenuTestConstants;
import fr.ninauve.kata.bankaccount.MessageTestConstants;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AcceptanceIT {

    private static final String INPUT_ACCOUNT_NUMBER = "123456789";
    private static final String INPUT_DEPOSIT_AMOUNT = "4200";
    private static final String INPUT_RETRIEVAL_AMOUNT = "1100";
    private static final String FORMATTED_DEPOSIT = "2020-09-12 11:43;123456789;deposit;4200;4200";
    private static final String FORMATTED_RETRIEVAL = "2020-09-12 11:43;123456789;retrieval;1100;3100";
    private static final List<String> FORMATTED_HISTORY = Arrays.asList(FORMATTED_DEPOSIT, FORMATTED_RETRIEVAL);

    private Main main;

    @Mock
    private Console console;

    @BeforeEach
    public void setUp() {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context-test.xml");
        final DelegateConsole delegateConsole = ctx.getBean(DelegateConsole.class);
        delegateConsole.setConsole(console);
        this.main = ctx.getBean(Main.class);
    }

    @Test
    public void should_exit() {

        when(console.waitAndGetInput())
                .thenReturn(MenuTestConstants.VALUE_EXIT);

        main.execute();

        final InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLines(MessageTestConstants.MENU);
        inOrder.verify(console).waitAndGetInput();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void should_deposit() {

        when(console.waitAndGetInput()).thenReturn(
                MenuTestConstants.VALUE_DEPOSIT,
                INPUT_ACCOUNT_NUMBER,
                INPUT_DEPOSIT_AMOUNT,
                MenuTestConstants.VALUE_EXIT);

        main.execute();

        verify(console).printLines(singletonList(FORMATTED_DEPOSIT));
    }

    @Test
    public void should_deposit_then_retrieve() {

        when(console.waitAndGetInput()).thenReturn(
                MenuTestConstants.VALUE_DEPOSIT,
                INPUT_ACCOUNT_NUMBER,
                INPUT_DEPOSIT_AMOUNT,
                MenuTestConstants.VALUE_RETRIEVAL,
                INPUT_ACCOUNT_NUMBER,
                INPUT_RETRIEVAL_AMOUNT,
                MenuTestConstants.VALUE_EXIT);

        main.execute();

        verify(console).printLines(singletonList(FORMATTED_RETRIEVAL));
    }

    @Test
    public void should_print_history() {

        when(console.waitAndGetInput()).thenReturn(
                MenuTestConstants.VALUE_DEPOSIT,
                INPUT_ACCOUNT_NUMBER,
                INPUT_DEPOSIT_AMOUNT,
                MenuTestConstants.VALUE_RETRIEVAL,
                INPUT_ACCOUNT_NUMBER,
                INPUT_RETRIEVAL_AMOUNT,
                MenuTestConstants.VALUE_HISTORY,
                INPUT_ACCOUNT_NUMBER,
                MenuTestConstants.VALUE_EXIT);

        main.execute();

        verify(console).printLines(FORMATTED_HISTORY);
    }
}
