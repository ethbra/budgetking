package com.budgetking.budgetking.repo;

import com.budgetking.budgetking.model.BudgetPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BudgetPlanRepository extends MongoRepository<BudgetPlan, String> {

}
