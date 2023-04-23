package com.blogging.service.service;

import com.blogging.service.enitities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    Optional<User> updateUserById(Long id, User newUser);
    void deleteUserById(Long id);
}

