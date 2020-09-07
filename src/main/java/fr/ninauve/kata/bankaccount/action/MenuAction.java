package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Scenario;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static fr.ninauve.kata.bankaccount.action.MenuItem.*;

@Component
public class MenuAction implements Action {

    private final Scenario scenario;
    private final Console console;
    private final Session session;

    @Autowired
    public MenuAction(Scenario scenario, Console console, Session session) {

        this.scenario = scenario;
        this.console = console;
        this.session = session;
    }

    @Override
    public Action execute() {

        console.printLines(MessageConstants.MENU);
        final String input = console.waitAndGetInput();
        if (!Objects.equals(input, DEPOSIT.getInputValue())
                && !Objects.equals(input, RETRIEVAL.getInputValue())
                && !Objects.equals(input, HISTORY.getInputValue())
                && !Objects.equals(input, EXIT.getInputValue())) {
            return badInput();
        }

        session.clear();
        if (Objects.equals(input, DEPOSIT.getInputValue())) {
            session.setMenuItem(DEPOSIT);
            scenario.initDepositOrRetrieval();
            return scenario.pollNextAction();
        }
        if (Objects.equals(input, RETRIEVAL.getInputValue())) {
            session.setMenuItem(RETRIEVAL);
            scenario.initDepositOrRetrieval();
            return scenario.pollNextAction();
        }
        if (Objects.equals(input, HISTORY.getInputValue())) {
            session.setMenuItem(HISTORY);
            scenario.initHistory();
            return scenario.pollNextAction();
        }

        return null;
    }

    private Action badInput() {

        console.printLines(List.of(MessageConstants.BAD_INPUT_MENU));
        return this;
    }
}
