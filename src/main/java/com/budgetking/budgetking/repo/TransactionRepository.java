package com.budgetking.budgetking.repo;

import com.budgetking.budgetking.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
