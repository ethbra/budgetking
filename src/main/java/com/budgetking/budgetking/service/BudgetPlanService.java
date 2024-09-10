package com.budgetking.budgetking.service;

import com.budgetking.budgetking.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetPlanService {
    private final UserRepository userRepository;

    @Autowired
    public BudgetPlanService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
