package com.budgetking.budgetking.service;

import com.budgetking.budgetking.model.User;
import com.budgetking.budgetking.repo.UserRepository;
import com.budgetking.budgetking.web.UserDto;
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

    public User save(UserDto user) {return userRepository.save(user);}

    public List<User> findAll(){return userRepository.findAll();}

    public Optional<User> updateUser(String id, UserDto updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            // Update fields
            if(updatedUser.getName() != null)
                existingUser.setName(updatedUser.getName());
            if(updatedUser.getEmail() != null)
                existingUser.setEmail(updatedUser.getEmail());
            if(updatedUser.getPassword() != null)
                existingUser.setPassword(updatedUser.getPassword());
            if(updatedUser.getCurrentBalance() < 0)
                existingUser.setCurrentBalance(updatedUser.getCurrentBalance());

            // Save updated user
            return userRepository.save(existingUser);
        });
    }
}
