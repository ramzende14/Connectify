package com.connectify.connectify10.Service;

import java.util.List;
import java.util.Optional;

import com.connectify.connectify10.entity.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id); // Corrected method name and return type

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    // Add more methods here related to the user
}
