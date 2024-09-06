package com.budgetking.budgetking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Document(collection = "users")
public class User {
    enum PayPeriod {Weekly, Biweekly, Monthly}

    @Id
    private String id;

    private String name;


    private String email;

    private String password;

    private float currentBalance;

    private PayPeriod payPeriod;

    private float payAmount;

    private BudgetPlan budgetPlan;

    private List<PlannedPurchase> plannedPurchases;
    private List<Transaction> transactions;


    public User() {
        name = null;
        email = null;
        password = null;
        currentBalance = 0;
        plannedPurchases = new ArrayList<>();
    }

    public User(String first, String email,
                String password, float currentBalance) {
        this.name = first;
        this.email = email;
        this.password = password;
        this.currentBalance = currentBalance;
        this.plannedPurchases = new ArrayList<>();
    }

    public User(String first, String email,
                String password, float currentBalance,
                BudgetPlan budgetPlan, List<Transaction> transactions) {
        this.name = first;
        this.email = email;
        this.password = password;
        this.currentBalance = currentBalance;
        this.budgetPlan = budgetPlan;
        this.plannedPurchases = new ArrayList<>();
        this.transactions = transactions;
    }
}
