package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.Transaction;
import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.service.TransactionService;
import com.budgetking.budgetking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class TransactionController {
    private final UserService userService;

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
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
        Optional<User> user = userService.findById(id);
        return user.map(value -> ResponseEntity.of(Optional.ofNullable(value.getTransactions())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


/* TODO: Put this in the TransactionController

    @PutMapping("/{id}/transaction")
    public User addTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        User mainUser = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));
        List<Transaction> trans = mainUser.getTransactions();
        trans.add(transaction);

        mainUser.setTransactions(trans);

        return userService.save(mainUser);
    }

    @RequestMapping("/get-by-email/{email}/transaction")
    public ResponseEntity<List<Transaction>> getTransactionByEmail(@PathVariable String email) {
        Optional<User> mainUser;
        try {
            mainUser = mainUserRepository.findByEmail(email);
        } catch (Exception ex) {
            return errMessage;
        }

        System.out.println("email = " + email);
        return ResponseEntity.ok(mainUser.getTransactions());
    }
    */

}
