package fr.ninauve.kata.bankaccount.action;

public enum MenuItem {
    DEPOSIT("1"), RETRIEVAL("2"), HISTORY("3"), EXIT("4");

    private final String inputValue;

    MenuItem(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getInputValue() {
        return inputValue;
    }
}
