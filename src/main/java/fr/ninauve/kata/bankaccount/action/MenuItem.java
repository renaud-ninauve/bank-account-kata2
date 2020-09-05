package fr.ninauve.kata.bankaccount.action;

public enum MenuItem {
    DEPOSIT("1"), EXIT("2");

    private final String inputValue;

    MenuItem(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getInputValue() {
        return inputValue;
    }
}
