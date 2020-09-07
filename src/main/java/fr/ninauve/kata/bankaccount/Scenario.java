package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

import static java.util.Arrays.asList;

@Component
public class Scenario {

    private LinkedList<Action> actions = new LinkedList<>();

    private ReadAccountNumberAction readAccountNumberAction;
    private ReadAmountAction readAmountAction;
    private DepositRetrievalAction depositRetrievalAction;
    private HistoryAction historyAction;
    private MenuAction menuAction;

    @Autowired
    public Scenario(@Lazy ReadAccountNumberAction readAccountNumberAction,
                    @Lazy ReadAmountAction readAmountAction,
                    @Lazy DepositRetrievalAction depositRetrievalAction,
                    @Lazy HistoryAction historyAction,
                    @Lazy MenuAction menuAction) {

        this.readAccountNumberAction = readAccountNumberAction;
        this.readAmountAction = readAmountAction;
        this.depositRetrievalAction = depositRetrievalAction;
        this.historyAction = historyAction;
        this.menuAction = menuAction;
    }

    public void initDepositOrRetrieval() {

        this.actions = new LinkedList<>(asList(
                readAccountNumberAction,
                readAmountAction,
                depositRetrievalAction
        ));
    }

    public void initHistory() {

        this.actions = new LinkedList<>(asList(
                readAccountNumberAction,
                historyAction
        ));
    }

    public Action pollNextAction() {

        return actions.isEmpty()
                ? menuAction : actions.poll();
    }
}
