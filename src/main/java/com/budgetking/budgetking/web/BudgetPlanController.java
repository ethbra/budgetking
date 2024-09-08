package com.budgetking.budgetking.web;

import com.budgetking.budgetking.service.BudgetPlanService;
import com.budgetking.budgetking.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class BudgetPlanController {
    private final BudgetPlanService budgetService;
    private final UserService userService;

    public BudgetPlanController(BudgetPlanService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }



    /*  TODO: Use this for the BudgetPlanController

    @PutMapping("/{id}/plan")
    public User updateUser(@PathVariable String id, @RequestBody BudgetPlan plan) {
        User mainUser = service.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));

        mainUser.setBudgetPlan(BudgetPlan.createPlan(plan.getMinBalance(), plan.isDoesInvesting()));

        return service.save(mainUser);
    }
*/

}
