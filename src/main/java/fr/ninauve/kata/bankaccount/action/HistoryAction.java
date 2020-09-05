package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.domain.Account;
import fr.ninauve.kata.bankaccount.domain.AccountRepository;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Component
public class HistoryAction implements Action {

    private final Console console;
    private final AccountRepository accountRepository;
    private final OperationFormatter operationFormatter;
    private final MenuAction menuAction;

    @Autowired
    public HistoryAction(Console console, AccountRepository accountRepository, OperationFormatter operationFormatter, @Lazy MenuAction menuAction) {

        this.console = console;
        this.accountRepository = accountRepository;
        this.operationFormatter = operationFormatter;
        this.menuAction = menuAction;
    }

    @Override
    public Action execute() {

        console.printLines(singletonList(MessageConstants.WHAT_ACCOUNT_NUMBER));
        final String accountNumber = console.waitAndGetInput();

        final Account account = accountRepository.getOrCreate(accountNumber);

        final List<String> formattedHistory = account.getHistoryOldestFirst().stream()
                .map(operation -> operationFormatter.format(accountNumber, operation))
                .collect(Collectors.toList());
        console.printLines(formattedHistory);
        return menuAction;
    }
}
