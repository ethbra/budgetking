package com.budgetking.budgetking.repo;

import com.budgetking.budgetking.model.PlannedPurchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlannedPurchaseRepository extends MongoRepository<PlannedPurchase, String> {

}
