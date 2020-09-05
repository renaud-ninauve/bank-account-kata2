package fr.ninauve.kata.bankaccount.it;

import fr.ninauve.kata.bankaccount.Main;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AcceptanceIT {

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
    public void should_print_hello_world() {

        main.execute();

        verify(console).printLines(Collections.singletonList("Hello world"));
    }
}
