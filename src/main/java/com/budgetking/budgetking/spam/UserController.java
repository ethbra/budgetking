package com.budgetking.budgetking.spam;

import com.budgetking.budgetking.model.BudgetPlan;
import com.budgetking.budgetking.model.Transaction;
import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class UserController {

    private UserService service;

    private final ResponseEntity<User> errMessage = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    @GetMapping
    public List<User> getAllMainUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getMainUserById(@PathVariable String id) {
        return service.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));
    }

    @GetMapping("/{id}/transaction")
    public List<Transaction> getTransactionById(@PathVariable String id) {
        try {
            return getMainUserById(id).getTransactions();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @RequestMapping("/get-by-email/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) {
        Optional<User> mainUser;
        mainUser = service.findByEmail(email);

        System.out.println("email = " + email);
        return ResponseEntity.ok(mainUser);
    }
/*
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
    }*/

    // TODO: make a better login that uses the service class :)
/*

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {

    }
*/

    static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping
    public User createMainUser(@RequestBody User mainUser) {
        try {
            service.save(mainUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mainUser;
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateMainUser(@PathVariable String id, @RequestBody User mainUserDetails) {
        Optional<User> userOptional = service.updateUser(id, mainUserDetails);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/plan")
    public User updateMainUser(@PathVariable String id, @RequestBody BudgetPlan plan) {
        User mainUser = service.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));

        mainUser.setBudgetPlan(BudgetPlan.createPlan(plan.getMinBalance(), plan.isDoesInvesting()));

        return service.save(mainUser);
    }

    @PutMapping("/{id}/transaction")
    public User addTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        User mainUser = service.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));
        List<Transaction> trans = mainUser.getTransactions();
        trans.add(transaction);

        mainUser.setTransactions(trans);

        return service.save(mainUser);
    }


    @DeleteMapping("/{id}")
    public String deleteMainUser(@PathVariable String id) {
        service.deleteById(id);
        return "mainUser deleted successfully";
    }
}
