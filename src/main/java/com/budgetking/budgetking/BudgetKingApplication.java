package com.budgetking.budgetking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.budgetking.budgetking.repo")
@SpringBootApplication
public class BudgetKingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetKingApplication.class, args);
    }

}
