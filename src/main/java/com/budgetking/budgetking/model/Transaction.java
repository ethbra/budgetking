package com.budgetking.budgetking.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter @Setter
public class Transaction {

    public enum Category {Housing, Transportation, Food, Groceries, Debt, Investments, Personal}

    @Id
    private String id;

    private String itemName;

    private Category cat;

    private Float cost;

    private Date date = new Date();

    private String description;

    private boolean isImpulse;


    private User mainUser;

    /**
     * Empty constructor for transaction
     */
    public Transaction() {
    }


    public Transaction(String itemName, Category cat, float cost, Date date, String description) {
        this.itemName = itemName;
        this.cat = cat;
        this.cost = cost;
        this.date = date;
        this.description = description;

    }
/**
 * This method creates an array of
    **/
    public static ArrayList<Transaction> create(String itemName, String cat,
                                                            float cost, Date date, String desc){
        Category realCategory = Category.Personal;
        for (Category _cat : Category.values()){
            if (cat.equals(_cat.toString()))
                realCategory = _cat;
        }


        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(itemName, realCategory, cost, date, desc));

        return transactions;
    }


    // Change this to an Arraylist<Transaction> for in/out
    // have it return ArrayList<Transaction> too.
    private void printIncomes(ArrayList<Transaction> transactionHistory) {
        for (Transaction trans : transactionHistory) {
            System.out.printf("%s, %s, %s", trans.getCost(), trans.getDate(), trans.getDescription());
        }
    }

    //    Looks at the past month and predicts weekly transactions
    public ArrayList<Transaction> predictIncome(ArrayList<Transaction> transactionHistory) {


        float rollingAverage = 0;
        for (Transaction transaction : transactionHistory) {
            Date now = new Date();
//            excludes transactions > 35 days ago for rolling average
            if ((now.getTime() - transaction.getDate().getTime() / 86400000.0) > 35) {
                rollingAverage += transaction.getCost();
            }
        }
//        gets weekly cost (35 days / 5)
        rollingAverage /= 5.0f;

//        Create future predicted +/- to balance as transaction[]
        Transaction[] transactions = new Transaction[10];
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

        /**Whatever calls this method will have to
         * sum the array to get current balance**/

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

    public boolean isImpulse() {
        return isImpulse;
    }

}
