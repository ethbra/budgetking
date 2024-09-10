package com.budgetking.budgetking.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter @Setter
public class Transaction {

    public enum Category {Housing, Transportation, Food, Groceries, Debt, Investments, Personal}

    private String itemName;

    private Category transactionType;

    private Float cost;

    private Date date = new Date();

    private String description;

    /**
     * Empty constructor for transaction
     */
    public Transaction() {
    }


    public Transaction(String itemName, Category transactionType, float cost, Date date, String description) {
        this.itemName = itemName;
        this.cost = cost;
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
    }
/**
 * This method creates an array of
    **/
    public static Transaction create(String itemName, Category cat,
                                                            float cost, Date date, String desc){
        return new Transaction(itemName, cat, cost, date, desc);
    }

    private void printTransactions(List<Transaction> transactionHistory) {
        for (Transaction trans : transactionHistory) {
            System.out.printf("%n %s: %s, %s, %s", trans.itemName, trans.getCost(), trans.getDate(), trans.getDescription());
        }
    }

    /**
     *  Whatever calls this method will have to sum the array to get current balance
     *
     * @param transactionHistory List of transactions in recent history
     * @return list of transactions with 10 additional datapoints for future predictions
     */
    //    Looks at the past month and predicts weekly transactions
    public List<Transaction> predictIncome(List<Transaction> transactionHistory) {

        float rollingAverage = 0;
        for (Transaction transaction : transactionHistory) {
            Date now = new Date();

            if ((now.getTime() - transaction.getDate().getTime() / 86400000.0) > 35) { //excludes transactions > 35 days ago for rolling average
                rollingAverage += transaction.getCost();
            }
        }
        rollingAverage /= 5.0f; //gets weekly cost (35 days / 5)

        Transaction[] transactions = new Transaction[10]; // Create future change to balance as transaction[]
        Date now = new Date();
        transactions[0] = new Transaction("Future Income",
                Category.Personal,
                rollingAverage,
                new Date(now.getTime() + 7 * 86400000),
                "Future income");
        for (int i = 1; i < transactions.length; i++) {
            transactions[i] = new Transaction("Future Income",
                    Category.Personal,
                    rollingAverage,
                    new Date(transactions[i - 1].getDate().getTime() + (7 * 86400000)),
                    "Future income");
        }
//        Add transactions to original ArrayList
        transactionHistory.addAll(Arrays.stream(transactions).toList());

/*
*/
        return transactionHistory;
    }
/*
    public boolean determineIsImpulse(Transaction transaction, BudgetPlan budget, User user) {
        float remainingBalance = user.getBalance() - transaction.getCost();

        boolean ans = remainingBalance < budget.getMinBalance();

        int count = 0;
//        If any previous transaction is bigger than this one, count it.
        for (int i = 0; i < user.getTransactions().size(); i++) {
            float itemCost = user.getTransactions().get(i).getCost();

            if (transaction.getCost() < itemCost)
                count++;
        }
//      If 80% of the previous transactions were bigger, then it is an impulse purchase.
        if (count >= (user.getTransactions().size() / .8))
            ans = true;

        return ans;
    }*/
}
