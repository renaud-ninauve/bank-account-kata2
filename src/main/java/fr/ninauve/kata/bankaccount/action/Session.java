package fr.ninauve.kata.bankaccount.action;

public class Session {

    private MenuItem menuItem;
    private String accountNumber;
    private long amount;

    public void clear() {

        this.menuItem = null;
        this.accountNumber = null;
        this.amount = 0l;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
