package fr.ninauve.kata.bankaccount.domain;

import com.google.common.base.Preconditions;

public class Account {
    private long balance;

    public void deposit(long amount) {
        Preconditions.checkArgument(amount > 0, "amount should be greater than 0");

        this.balance += amount;
    }

    public long getBalance() {
        return balance;
    }
}
