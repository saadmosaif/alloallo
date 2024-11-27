package com.alloallo.controller;

import com.alloallo.model.User;
import com.alloallo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestParam String username) {
        return userService.registerUser(username);
    }

    @PutMapping("/{username}/status")
    public void updateUserStatus(@PathVariable String username, @RequestParam String status) {
        userService.updateUserStatus(username, status);
    }

    @GetMapping("/available")
    public List<User> getAvailableUsers() {
        return userService.getAllAvailableUsers();
    }
}
