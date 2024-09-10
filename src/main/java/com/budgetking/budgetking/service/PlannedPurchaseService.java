package com.budgetking.budgetking.service;

import com.budgetking.budgetking.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedPurchaseService {
    private final UserRepository userRepository;

    @Autowired
    public PlannedPurchaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
