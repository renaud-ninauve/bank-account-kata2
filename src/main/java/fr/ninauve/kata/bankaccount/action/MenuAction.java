package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuAction implements Action {

    private final Console console;

    @Autowired
    public MenuAction(Console console) {

        this.console = console;
    }

    @Override
    public Action execute() {

        return null;
    }
}
