package com.budgetking.budgetking.service;

import com.budgetking.budgetking.model.BudgetPlan;
import com.budgetking.budgetking.repo.BudgetPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetPlanService {
    private final BudgetPlanRepository budgetPlanRepository;


    @Autowired
    public BudgetPlanService(BudgetPlanRepository budgetPlanRepository) {
        this.budgetPlanRepository = budgetPlanRepository;
    }

    public List<BudgetPlan> getAll() {return budgetPlanRepository.findAll();}
}
