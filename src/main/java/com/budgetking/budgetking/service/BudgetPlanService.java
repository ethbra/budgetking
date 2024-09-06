package com.budgetking.budgetking.service;

import com.budgetking.budgetking.repo.BudgetPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetPlanService {
    private final BudgetPlanRepository budgetPlanRepository;


    @Autowired
    public BudgetPlanService(BudgetPlanRepository budgetPlanRepository) {
        this.budgetPlanRepository = budgetPlanRepository;
    }
}
