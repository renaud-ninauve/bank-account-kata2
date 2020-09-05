package fr.ninauve.kata.bankaccount.it;

import fr.ninauve.kata.bankaccount.io.Console;

import java.util.List;

public class DelegateConsole implements Console {

    private Console console;

    @Override
    public void printLines(List<String> lines) {
        console.printLines(lines);
    }

    @Override
    public String waitAndGetInput() {
        return console.waitAndGetInput();
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
