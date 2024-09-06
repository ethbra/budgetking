package com.budgetking.budgetking.repo;

import com.budgetking.budgetking.model.BudgetPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanRepository extends MongoRepository<BudgetPlan, String> {

}
