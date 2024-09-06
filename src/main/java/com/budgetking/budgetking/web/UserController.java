package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.Transaction;
import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/* TODO: remove extra APIs generated from models (budget-plan-entity-controller, etc.)
   TODO: Resetting Spring Boot (with database service running) doubles the entries
*/

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost")
public class UserController {

    private final UserService userService;

    private final ResponseEntity<User> errMessage = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllMainUsers() {
        return userService.findAll();
    }
    /**
     * Get a user by their ID.
     *
     * @param id
     */
    @GetMapping("/{id}")
    public User getMainUserById(@PathVariable(value = "userId") String id) {
        return userService.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));
    }
    /**
     * Get a list of transactions by the user's ID.
     *
     * @param id
     */
    @GetMapping("/{id}/transaction")
    public List<Transaction> getTransactionById(@PathVariable String id) {
        try {
            return getMainUserById(id).getTransactions();
        } catch (RuntimeException e) {
            throw e;
        }
    }
    /**
     * Get a user by their email address.
     * @return ResponseEntity<User>
     *
     * @param email
     */
    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) { //TODO fix this return object
        Optional<User> mainUser;
        mainUser = userService.findByEmail(email);

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
    /**
     * Object for login requests
     *
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

/**
 *  Add a user to the database.
 *
 *  @param mainUser - The UserDTO object
*/
    @PostMapping
    public ResponseEntity<UserDto> createMainUser(@RequestBody UserDto mainUser) {
        User user = userService.createUser(mainUser);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mainUser);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
/**
 * Remove a user from the database.
 *
 * @param id
 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMainUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Change one of the user's values.
     * @apiNote This will take any object
     *
     * @param id
     * @param mainUserDetails - UserDTO object
     */
    @PutMapping("/{id}")
    @Operation(summary = "Change one of the user's values.")
    public ResponseEntity<User> updateMainUser(@PathVariable String id, @RequestBody @Valid UserDto mainUserDetails) {
        Optional<User> userOptional = userService.updateUser(id, mainUserDetails);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
/*  TODO: Use this for the BudgetPlanController

    @PutMapping("/{id}/plan")
    public User updateMainUser(@PathVariable String id, @RequestBody BudgetPlan plan) {
        User mainUser = service.findById(id)
                .orElseThrow(() -> new RuntimeException("mainUser not found with id: " + id));

        mainUser.setBudgetPlan(BudgetPlan.createPlan(plan.getMinBalance(), plan.isDoesInvesting()));

        return service.save(mainUser);
    }
*/
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
*/
}
