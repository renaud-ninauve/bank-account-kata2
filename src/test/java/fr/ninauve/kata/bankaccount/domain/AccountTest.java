package fr.ninauve.kata.bankaccount.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    public void balance_is_zero_when_no_operations() {

        final Account account = new Account();

        final long actual = account.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void fail_when_deposit_amount_is_negative() {

        final Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
    }

    @Test
    public void fail_when_deposit_amount_is_zero() {

        final Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    public void balance_is_equal_to_deposit_when_one_deposit() {

        final Account account = new Account();
        account.deposit(4200);

        final long actual = account.getBalance();

        assertEquals(4200, actual);
    }
}