package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.domain.Account;
import fr.ninauve.kata.bankaccount.domain.AccountRepository;
import fr.ninauve.kata.bankaccount.domain.Operation;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZonedDateTime;

import static java.util.Collections.singletonList;

@Component
public class DepositRetrievalAction implements Action {

    private final Console console;
    private final Session session;
    private final Clock clock;
    private final AccountRepository accountRepository;
    private final MenuAction menuAction;
    private final OperationFormatter operationFormatter;

    @Autowired
    public DepositRetrievalAction(Console console, Session session, Clock clock, AccountRepository accountRepository, @Lazy MenuAction menuAction, OperationFormatter operationFormatter) {
        this.console = console;
        this.session = session;
        this.clock = clock;
        this.accountRepository = accountRepository;
        this.menuAction = menuAction;
        this.operationFormatter = operationFormatter;
    }

    @Override
    public Action execute() {

        final String accountNumber = session.getAccountNumber();
        final long amount = session.getAmount();
        final ZonedDateTime dateTime = ZonedDateTime.now(clock);

        final Account account = accountRepository.getOrCreate(accountNumber);
        if (session.getMenuItem() == MenuItem.DEPOSIT) {
            account.deposit(amount, dateTime);
        } else {
            account.retrieval(amount, dateTime);
        }

        final Operation lastOperation = account.getLastOperationOrNull();
        final String formattedOperation = operationFormatter.format(accountNumber, lastOperation);
        console.printLines(singletonList(formattedOperation));

        return menuAction;

    }
}
