package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.domain.Account;
import fr.ninauve.kata.bankaccount.domain.AccountRepository;
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

        final Account account = accountRepository.getOrCreate(accountNumber);
        final OperationFormatter.OperationType operationType;
        if (session.getMenuItem() == MenuItem.DEPOSIT) {
            operationType = OperationFormatter.OperationType.DEPOSIT;
            account.deposit(amount);
        } else {
            operationType = OperationFormatter.OperationType.RETRIEVAL;
            account.retrieval(amount);
        }

        final long balance = account.getBalance();
        final String formattedOperation = operationFormatter.format(
                ZonedDateTime.now(clock), accountNumber, operationType, amount, balance);
        console.printLines(singletonList(formattedOperation));
        return menuAction;
    }
}
