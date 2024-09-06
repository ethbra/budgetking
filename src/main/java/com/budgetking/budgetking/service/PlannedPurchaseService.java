package com.budgetking.budgetking.service;

import com.budgetking.budgetking.repo.PlannedPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedPurchaseService {

    private final PlannedPurchaseRepository plannedPurchaseRepo;

    @Autowired
    public PlannedPurchaseService(PlannedPurchaseRepository plannedPurchaseRepo) {
        this.plannedPurchaseRepo = plannedPurchaseRepo;
    }
}
