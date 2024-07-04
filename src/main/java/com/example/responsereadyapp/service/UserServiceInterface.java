package com.example.responsereadyapp.service;

import com.example.responsereadyapp.dto.*;
import com.example.responsereadyapp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<UserResponseDto> getAllUsers();
    Optional<UserResponseDto> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    String registerUser(UserRegistrationDto registrationDto);
    String loginUser(LoginDto loginDto);
    Optional<User> findUserByEmail(String email);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    List<User> getUsersByRole(String role);
    Optional<UserResponseDto> getUserDetailsByToken(String token);
    UserResponseDto getUserByEmail(String email);
}
