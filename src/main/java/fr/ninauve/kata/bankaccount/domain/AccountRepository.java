package fr.ninauve.kata.bankaccount.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    public Account getOrCreate(final String accountNumber) {

        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new Account());
        }
        return accounts.get(accountNumber);
    }
}
