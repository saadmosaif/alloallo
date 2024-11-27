package com.alloallo.service;

import com.alloallo.model.User;
import com.alloallo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setStatus("available");
        return userRepository.save(user);
    }

    public void updateUserStatus(String username, String status) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(status);
        userRepository.save(user);
    }

    public List<User> getAllAvailableUsers() {
        return userRepository.findAll().stream()
                .filter(user -> "available".equals(user.getStatus()))
                .toList();
    }
}
