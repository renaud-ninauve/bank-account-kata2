package fr.ninauve.kata.bankaccount.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {

        this.accountRepository = new AccountRepository();
    }

    @Test
    public void should_create_account_when_doesnt_exist() {

        final Account actual = accountRepository.getOrCreate("1234");

        assertNotNull(actual);
    }

    @Test
    public void should_create_different_account_for_different_account_number() {

        final Account actual1 = accountRepository.getOrCreate("1234");
        final Account actual2 = accountRepository.getOrCreate("5678");

        assertNotNull(actual1);
        assertNotNull(actual2);
        assertNotSame(actual2, actual1);
    }

    @Test
    public void should_return_same_instance_for_same_account_number() {

        final Account actual1 = accountRepository.getOrCreate("1234");
        final Account actual2 = accountRepository.getOrCreate("1234");

        assertNotNull(actual1);
        assertNotNull(actual2);
        assertSame(actual2, actual1);
    }
}