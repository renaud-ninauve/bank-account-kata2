package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Scenario;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class ReadAmountAction implements Action {

    private final Scenario scenario;
    private final Console console;
    private final Session session;
    private final AmountInputValidator amountInputValidator;

    @Autowired
    public ReadAmountAction(Scenario scenario, Console console, Session session, AmountInputValidator amountInputValidator) {

        this.scenario = scenario;
        this.console = console;
        this.session = session;
        this.amountInputValidator = amountInputValidator;
    }

    @Override
    public Action execute() {

        console.printLines(singletonList(MessageConstants.WHAT_AMOUNT));
        final String input = console.waitAndGetInput();
        if (amountInputValidator.isValid(input)) {
            session.setAmount(Long.parseLong(input));
            return scenario.pollNextAction();
        }

        console.printLines(singletonList(MessageConstants.BAD_PARAM_AMOUNT));
        return this;
    }
}
