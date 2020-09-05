package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.Action;
import fr.ninauve.kata.bankaccount.action.MenuAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainTest {

    private Main main;

    @Mock
    private MenuAction menuAction;
    @Mock
    private Action nextAction;

    @BeforeEach
    public void setUp() {

        this.main = new Main(menuAction);
    }

    @Test
    public void should_execute_next_action() {

        when(menuAction.execute()).thenReturn(nextAction);
        when(nextAction.execute()).thenReturn(null);

        main.execute();

        verify(menuAction).execute();
        verify(nextAction).execute();
    }
}