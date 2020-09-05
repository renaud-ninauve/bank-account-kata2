package fr.ninauve.kata.bankaccount.action;

import java.util.regex.Pattern;

public class AmountInputValidator {

    private static final Pattern PATTERN_AMOUNT = Pattern.compile("[1-9]\\d{0,15}");

    public boolean isValid(final String input) {

        return PATTERN_AMOUNT.matcher(input).matches();
    }
}
