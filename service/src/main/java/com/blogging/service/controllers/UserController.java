package com.blogging.service.controllers;

import com.blogging.service.enitities.User;
import com.blogging.service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // CREATE a new user
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // UPDATE a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @Valid @RequestBody User newUser) {
        Optional<User> optionalUser = userService.updateUserById(id, newUser);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}


