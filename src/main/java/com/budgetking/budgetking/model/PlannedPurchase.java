package com.budgetking.budgetking.model;
import org.springframework.data.annotation.Id;

import java.util.*;


public class PlannedPurchase {

    String category;

    @Id
    private String id;

    String itemName;

    private float cost;

    private Date goalDate;

    private BudgetPlan plan;


    public PlannedPurchase() {
        plan = null;
        itemName = null;
        cost = 0;
        goalDate = null;
        category = "";
    }


    public PlannedPurchase(String item, float cost, Date goalDate, String category1) {
        this.itemName = item;
        this.cost = cost;
        this.goalDate = goalDate;
        this.category = category1;
        this.plan = null;
    }


    public PlannedPurchase createPurchase(String item, float cost,
                                          Date goalDate, String category) {

        return new PlannedPurchase(item, cost, goalDate, category);
    }
}
