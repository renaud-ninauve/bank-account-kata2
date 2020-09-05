package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static fr.ninauve.kata.bankaccount.action.MenuItem.DEPOSIT;
import static fr.ninauve.kata.bankaccount.action.MenuItem.EXIT;

@Component
public class MenuAction implements Action {

    private final Console console;
    private final Session session;
    private final ReadAccountNumberAction readAccountNumberAction;

    @Autowired
    public MenuAction(Console console, Session session, @Lazy ReadAccountNumberAction readAccountNumberAction) {

        this.console = console;
        this.session = session;
        this.readAccountNumberAction = readAccountNumberAction;
    }

    @Override
    public Action execute() {

        console.printLines(MessageConstants.MENU);
        final String input = console.waitAndGetInput();
        if (!Objects.equals(input, DEPOSIT.getInputValue())
                && !Objects.equals(input, EXIT.getInputValue())) {
            return badInput();
        }

        session.clear();
        if (Objects.equals(input, DEPOSIT.getInputValue())) {
            session.setMenuItem(DEPOSIT);
            return readAccountNumberAction;
        }

        return null;
    }

    private Action badInput() {

        console.printLines(List.of(MessageConstants.BAD_INPUT_MENU));
        return this;
    }
}
