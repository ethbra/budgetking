package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Returns a JSON list of all Users in the repository")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Get a user by their ID.
     *
     * @param id User's ID in Mongo Database.
     *
     */
    @Operation(summary = "returns the fields for a user by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        Optional<User> user = userService.findById(id);
        return user.isPresent() ? ResponseEntity.of(user) : ResponseEntity.notFound().build();
    }

    /**
     * Get a user by their email address.
     *
     * @param email
     * @return ResponseEntity<User>
     */
    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) { //TODO fix this return object
        Optional<User> mainUser;
        mainUser = userService.findByEmail(email);

        System.out.println("email = " + email);
        return ResponseEntity.ok(mainUser);
    }

    // TODO: make a better login that uses the service class :)

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());
        return user.isPresent() ? ResponseEntity.of(user) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Object for login requests
     */
    @Getter
    @Setter
    static class LoginRequest {
        private String email;
        private String password;
    }

    /**
     * Add a user to the database.
     *
     * @param mainUser - The UserDTO object
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto mainUser) {
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
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Change one/some of the user's values.")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody @Valid UserDto dto) {
        logger.info("Received request to update user with id: {}", id);
        logger.info("Request parameters - n: {}, e: {}, pw: {}, cb: {}, pp: {}, p: {}", dto.getName(), dto.getEmail(), dto.getPassword(), dto.getCurrentBalance(), dto.getPayPeriod(), dto.getPayAmount());

        Optional<User> userOptional = userService.updateSome(id, dto);

        if (userOptional.isPresent()) {
            logger.info("User with id: {} updated successfully", id);
            return ResponseEntity.ok(userOptional.get());
        } else {
            logger.warn("User with id: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}
