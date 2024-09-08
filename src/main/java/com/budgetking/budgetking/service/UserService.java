package com.budgetking.budgetking.service;

import com.budgetking.budgetking.model.PayPeriod;
import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.repo.UserRepository;
import com.budgetking.budgetking.web.UserController;
import com.budgetking.budgetking.web.UserDto;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(@NotNull UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCurrentBalance(userDto.getCurrentBalance());
        user.setPayAmount(userDto.getPayAmount());
        user.setPayPeriod(userDto.getPayPeriod());

        return this.save(user);
    }

    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(String id, UserDto updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            // Update fields
            if (updatedUser.getName() != null)
                existingUser.setName(updatedUser.getName());
            if (updatedUser.getEmail() != null)
                existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null)
                existingUser.setPassword(updatedUser.getPassword());
            if (updatedUser.getCurrentBalance() < 0)
                existingUser.setCurrentBalance(updatedUser.getCurrentBalance());

            // Save updated user
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> updateSome(String id, UserDto userInfo) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return Optional.empty();
        logger.info("User was found, updating fields... ");

        //TODO There is probably something wrong with this but I'm keeping it for now :)
        if (userInfo.getName() != null) user.setName(userInfo.getName());
        if (userInfo.getEmail() != null) user.setEmail(userInfo.getEmail());
        if (userInfo.getPassword() != null) user.setPassword(userInfo.getPassword());
        if (userInfo.getCurrentBalance() > 0) user.setCurrentBalance(userInfo.getCurrentBalance());
        if (userInfo.getPayAmount() > 0) user.setPayAmount(userInfo.getPayAmount());
        if (userInfo.getPayPeriod() != null) user.setPayPeriod(userInfo.getPayPeriod());
        return Optional.of(userRepository.save(user));

    }

    public boolean deleteUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
