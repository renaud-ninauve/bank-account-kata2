package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class ReadAccountNumberAction implements Action {

    private final Console console;
    private final Session session;
    private final ReadAmountAction readAmountAction;

    @Autowired
    public ReadAccountNumberAction(Console console, Session session, ReadAmountAction readAmountAction) {

        this.console = console;
        this.session = session;
        this.readAmountAction = readAmountAction;
    }

    @Override
    public Action execute() {

        console.printLines(singletonList(MessageConstants.WHAT_ACCOUNT_NUMBER));
        final String accountNumber = console.waitAndGetInput();
        session.setAccountNumber(accountNumber);
        return readAmountAction;
    }
}
