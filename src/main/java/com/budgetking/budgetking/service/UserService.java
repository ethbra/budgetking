package com.budgetking.budgetking.service;

import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.repo.UserRepository;
import com.budgetking.budgetking.web.UserDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
        if( userRepository.findByEmail(user.getEmail()).isPresent() ) {
            return null;
        }
            return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    // TODO: Create updateUser method with parameters for the User arguments.
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

    public boolean deleteUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
