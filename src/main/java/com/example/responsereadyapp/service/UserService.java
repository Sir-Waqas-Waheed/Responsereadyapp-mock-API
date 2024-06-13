package com.example.responsereadyapp.service;


import com.example.responsereadyapp.entity.user;
import com.example.responsereadyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<user> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public user createUser(user user) {
        return userRepository.save(user);
    }

    public user updateUser(Long id, user userDetails) {
        user user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}