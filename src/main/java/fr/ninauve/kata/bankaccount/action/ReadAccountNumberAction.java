package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Scenario;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class ReadAccountNumberAction implements Action {

    private final Scenario scenario;
    private final Console console;
    private final Session session;

    @Autowired
    public ReadAccountNumberAction(Scenario scenario, Console console, Session session) {

        this.scenario = scenario;
        this.console = console;
        this.session = session;
    }

    @Override
    public Action execute() {

        console.printLines(singletonList(MessageConstants.WHAT_ACCOUNT_NUMBER));
        final String accountNumber = console.waitAndGetInput();
        session.setAccountNumber(accountNumber);
        return scenario.pollNextAction();
    }
}
