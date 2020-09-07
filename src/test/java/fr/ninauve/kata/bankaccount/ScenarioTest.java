package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class ScenarioTest {

    private Scenario scenario;

    @Mock
    private ReadAccountNumberAction readAccountNumberAction;
    @Mock
    private ReadAmountAction readAmountAction;
    @Mock
    private DepositRetrievalAction depositRetrievalAction;
    @Mock
    private HistoryAction historyAction;
    @Mock
    private MenuAction menuAction;

    @BeforeEach
    public void setUp() {

        this.scenario = new Scenario(
                readAccountNumberAction, readAmountAction, depositRetrievalAction, historyAction, menuAction);
    }

    @Test
    public void should_return_menu_if_not_initiated_when_pollNextAction() {

        final Action actual = scenario.pollNextAction();

        assertSame(menuAction, actual);
    }

    @Test
    public void should_not_return_menu_after_deposit_initiated_when_pollNextAction() {

        scenario.initDepositOrRetrieval();

        final Action actual = scenario.pollNextAction();

        assertNotSame(menuAction, actual);
    }

    @Test
    public void should_not_return_menu_after_history_initiated_when_pollNextAction() {

        scenario.initHistory();

        final Action actual = scenario.pollNextAction();

        assertNotSame(menuAction, actual);
    }
}