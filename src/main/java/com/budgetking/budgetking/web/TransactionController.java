package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.Transaction;
import com.budgetking.budgetking.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Get a list of transactions by the user's ID.
     *
     * @param id - User's ID in Mongo Database.
     */
    @Operation(summary = "returns the list of transactions for the user with ID")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsById(@PathVariable String id) {
        Optional<List<Transaction>> transactions = transactionService.getTransactionsById(id);
        return transactions.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Given a user ID and transaction, the transaction will be added to the User's data")
    @PostMapping("/{id}/transaction")
    public ResponseEntity<List<Transaction>> addTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        Optional<List<Transaction>> transactions = transactionService.addTransaction(id, transaction);
        String str = transactions.isPresent() ? "Transactions have returned {}" : "Transaction add failed.";
        logger.info(str);
        return transactions.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
