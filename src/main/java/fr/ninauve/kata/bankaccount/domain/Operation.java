package fr.ninauve.kata.bankaccount.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Operation {

    public enum OperationType { DEPOSIT, RETRIEVAL }

    private final OperationType operationType;
    private final ZonedDateTime dateTime;
    private final long amount;
    private final long balance;

    public Operation(OperationType operationType, ZonedDateTime dateTime, long amount, long balance) {
        this.operationType = operationType;
        this.dateTime = dateTime;
        this.amount = amount;
        this.balance = balance;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return amount == operation.amount &&
                balance == operation.balance &&
                operationType == operation.operationType &&
                Objects.equals(dateTime, operation.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, dateTime, amount, balance);
    }
}
