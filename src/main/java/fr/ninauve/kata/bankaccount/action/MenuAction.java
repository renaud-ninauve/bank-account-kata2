package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MenuAction implements Action {

    private final Console console;

    @Autowired
    public MenuAction(Console console) {

        this.console = console;
    }

    @Override
    public Action execute() {

        console.printLines(MessageConstants.MENU);
        final String input = console.waitAndGetInput();
        if (!Objects.equals(input, "2")) {
            return badInput();
        }

        return null;
    }

    private Action badInput() {

        console.printLines(List.of(MessageConstants.BAD_INPUT_MENU));
        return this;
    }
}
