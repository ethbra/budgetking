package com.budgetking.budgetking.repo;

import com.budgetking.budgetking.model.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Override
    Optional<Transaction> findById(String s);
}
