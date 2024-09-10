package com.budgetking.budgetking.service;

import com.budgetking.budgetking.model.Transaction;
import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TransactionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<List<Transaction>> getTransactionsById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent() && user.get().getTransactions() == null || user.get().getTransactions().isEmpty()) { // Always returns empty, so difficult to debug

            return Optional.empty();
        }
        return user.map(User::getTransactions);

    }
    public Optional<List<Transaction>> addTransaction(String id, Transaction transaction) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            List<Transaction> trans = user.get().getTransactions() != null ? user.get().getTransactions() : new ArrayList<Transaction>();
            trans.add(transaction);
            user.get().setTransactions(trans);
            userRepository.save(user.get()); // I really hope this works D:
            return Optional.of(trans);
        }
        return Optional.empty();
    }

}
