package com.budgetking.budgetking.repo;

import com.budgetking.budgetking.model.PlannedPurchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlannedPurchaseRepository extends MongoRepository<PlannedPurchase, String> {

}
