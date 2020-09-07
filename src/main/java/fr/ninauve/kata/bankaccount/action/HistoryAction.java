package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Scenario;
import fr.ninauve.kata.bankaccount.domain.Account;
import fr.ninauve.kata.bankaccount.domain.AccountRepository;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryAction implements Action {

    private final Scenario scenario;
    private final Console console;
    private final Session session;
    private final AccountRepository accountRepository;
    private final OperationFormatter operationFormatter;

    @Autowired
    public HistoryAction(Scenario scenario, Console console, Session session, AccountRepository accountRepository, OperationFormatter operationFormatter) {

        this.scenario = scenario;
        this.console = console;
        this.session = session;
        this.accountRepository = accountRepository;
        this.operationFormatter = operationFormatter;
    }


    @Override
    public Action execute() {

        final String accountNumber = session.getAccountNumber();

        final Account account = accountRepository.getOrCreate(accountNumber);

        final List<String> formattedHistory = account.getHistoryOldestFirst().stream()
                .map(operation -> operationFormatter.format(accountNumber, operation))
                .collect(Collectors.toList());
        console.printLines(formattedHistory);
        return scenario.pollNextAction();
    }
}
