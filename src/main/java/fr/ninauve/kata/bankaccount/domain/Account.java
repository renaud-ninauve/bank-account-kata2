package fr.ninauve.kata.bankaccount.domain;

import com.google.common.base.Preconditions;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Operation> historyOldestFirst = new ArrayList<>();

    public void deposit(long amount, ZonedDateTime dateTime) {
        Preconditions.checkArgument(amount > 0, "amount should be greater than 0");
        Preconditions.checkNotNull(dateTime, "date shouldn't be null");

        final long newBalance = getBalance() + amount;
        final Operation operation = new Operation(Operation.OperationType.DEPOSIT, dateTime, amount, newBalance);
        historyOldestFirst.add(operation);
    }

    public void retrieval(long amount, ZonedDateTime dateTime) {
        Preconditions.checkArgument(amount > 0, "amount should be greater than 0");
        Preconditions.checkNotNull(dateTime, "date shouldn't be null");

        final long newBalance = getBalance() - amount;
        final Operation operation = new Operation(Operation.OperationType.RETRIEVAL, dateTime, amount, newBalance);
        historyOldestFirst.add(operation);
    }

    public long getBalance() {
        if (historyOldestFirst.isEmpty()) {
            return 0;
        }

        return historyOldestFirst.get(historyOldestFirst.size() - 1).getBalance();
    }

    public List<Operation> getHistoryOldestFirst() {

        return new ArrayList<>(historyOldestFirst);
    }

    public Operation getLastOperationOrNull() {

        return historyOldestFirst.isEmpty() ? null : historyOldestFirst.get(historyOldestFirst.size() - 1);
    }
}
