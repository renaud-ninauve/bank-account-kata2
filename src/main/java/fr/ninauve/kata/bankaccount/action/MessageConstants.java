package fr.ninauve.kata.bankaccount.action;

import java.util.List;

import static java.util.Arrays.asList;

public interface MessageConstants {

    String WHAT_ACCOUNT_NUMBER = "Account number?";
    String WHAT_AMOUNT = "Amount in cents?";
    String BAD_PARAM_AMOUNT = "Amount should be in cents (numbers only, no signs).";
    String BAD_INPUT_MENU = "Should be 1 or 2";
    List<String> MENU = asList("What do you want to do?", "1. Deposit", "2. Retrieval", "3. Exit");
}
