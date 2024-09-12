package com.budgetking.budgetking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class BudgetPlan {

    private boolean doesInvesting;
    private float minBalance;
    boolean foundPurchase;

    public BudgetPlan() {
        doesInvesting = false;
        minBalance = 0.0f;
    }

    public BudgetPlan(float minBalance, boolean doesInvesting) {
        this.minBalance = minBalance;
        this.doesInvesting = doesInvesting;
    }

    public static BudgetPlan createPlan(float minBal, boolean doesInvesting) {
        BudgetPlan plan = new BudgetPlan(minBal, doesInvesting);
        return plan;
    }


/*
    public PlannedPurchase findFuturePurchases(String itemName)
    {
        PlannedPurchase purchase = null;
        foundPurchase = false;

        for (PlannedPurchase p : futurePurchases)
        {
            if(itemName.equals(p.itemName))
            {
                purchase = p;
                foundPurchase = true;

                break;
            }
        }

        if(foundPurchase)
            return purchase;
        else
            return null;
    }
*/
}
