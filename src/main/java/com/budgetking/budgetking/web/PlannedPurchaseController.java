package com.budgetking.budgetking.web;

import com.budgetking.budgetking.service.PlannedPurchaseService;
import com.budgetking.budgetking.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class PlannedPurchaseController {
    private final PlannedPurchaseService plannedPurchaseService;
    private final UserService userService;

    public PlannedPurchaseController(PlannedPurchaseService plannedPurchaseService, UserService userService) {
        this.plannedPurchaseService = plannedPurchaseService;
        this.userService = userService;
    }


}
