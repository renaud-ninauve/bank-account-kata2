package fr.ninauve.kata.bankaccount;

import java.util.List;

import static java.util.Arrays.asList;

public interface MessageTestConstants {

    String WHAT_ACCOUNT_NUMBER = "Account number?";
    String WHAT_AMOUNT = "Amount in cents?";
    String BAD_PARAM_AMOUNT = "Amount should be in cents (numbers only, no signs).";
    String BAD_INPUT_MENU = "Should be one of (1, 2, 3, 4)";
    List<String> MENU = asList(
            "What do you want to do?",
            "1. Deposit",
            "2. Retrieval",
            "3. History",
            "4. Exit");
}
