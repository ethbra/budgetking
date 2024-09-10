package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
     * @param email full email the user signed up with (name@example.com)
     * @return ResponseEntity<User> - The HTTP response, which will return the User fields if successful
     */
    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) { //TODO fix this return object
        Optional<User> mainUser = userService.findByEmail(email);
        if (mainUser.isPresent()) {
            return ResponseEntity.ok(mainUser);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "takes strings {email}, {password} and returns User if they match", method = "userService.login")
    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody @Valid LoginDto rq) {
        logger.info("email: {}, password: {}", rq.getEmail(), rq.getPassword());
        Optional<User> user = userService.login(rq.getEmail(), rq.getPassword());

        if (user.isPresent()) {
            logger.info("Successful login of user {}", user.get().getName());
            return ResponseEntity.of(Optional.of(user));
        }

        logger.info("Failed login, returning Status 401");
        return ResponseEntity.status(401).build();
    }

    /**
     * Add a user to the database.
     *
     * @param dtoUser - The UserDTO object
     */
    @Operation(summary = "Add a user to the repository")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto dtoUser) {
        User user = userService.createUser(dtoUser);
        if (user != null) {
            return ResponseEntity.status(201).body(user);
        }
        logger.info("User with this email already exists");
        return ResponseEntity.status(409).body(null);
    }

    /**
     * Remove a user from the database.
     *
     * @param id
     */
    @Operation(summary = "*** CAREFUL *** permanently deletes user {id}!")
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
