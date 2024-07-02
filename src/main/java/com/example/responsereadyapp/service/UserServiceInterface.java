package com.example.responsereadyapp.service;

import com.example.responsereadyapp.dto.*;
import com.example.responsereadyapp.entity.User;
import org.springframework.security.core.userdetails.*;

import java.util.*;


public interface UserServiceInterface extends UserDetailsService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    String registerUser(UserRegistrationDto registrationDto);
    String loginUser(LoginDto loginDto);
}
