package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class TransactionController {


    /**
     * Get a list of transactions by the user's ID.
     *
     * @param id - User's ID in Mongo Database.
     */
    @GetMapping("/{id}/transaction")
    public List<Transaction> getTransactionById(@PathVariable String id) {
        }
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
