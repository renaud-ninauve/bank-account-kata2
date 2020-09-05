package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class ReadAmountAction implements Action {

    private final Console console;
    private final Session session;
    private final DepositAction depositAction;
    private final AmountInputValidator amountInputValidator;

    @Autowired
    public ReadAmountAction(Console console, Session session, @Lazy DepositAction depositAction, AmountInputValidator amountInputValidator) {
        this.console = console;
        this.session = session;
        this.depositAction = depositAction;
        this.amountInputValidator = amountInputValidator;
    }

    @Override
    public Action execute() {

        console.printLines(singletonList(MessageConstants.WHAT_AMOUNT));
        final String input = console.waitAndGetInput();
        if (amountInputValidator.isValid(input)) {
            session.setAmount(Long.parseLong(input));
            return depositAction;
        }

        console.printLines(singletonList(MessageConstants.BAD_PARAM_AMOUNT));
        return this;
    }
}
