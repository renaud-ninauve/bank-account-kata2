package fr.ninauve.kata.bankaccount.io;

import java.util.List;

public interface Console {

    void printLines(List<String> lines);

    String waitAndGetInput();
}
