package com.example.responsereadyapp.controller;

import com.example.responsereadyapp.dto.*;
import com.example.responsereadyapp.entity.*;
import com.example.responsereadyapp.service.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordResetService passwordResetService;
    private final MessageSource messages;

    public UserController(@Lazy UserService userService, @Lazy TokenService tokenService, @Lazy PasswordResetService passwordResetService, MessageSource messages) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordResetService = passwordResetService;
        this.messages = messages;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        String token = userService.registerUser(registrationDto);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginDto) {
        String token = userService.loginUser(loginDto);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logoutUser(@RequestHeader("Authorization") String token) {
        token = token.substring(7); // Remove "Bearer " prefix
        tokenService.invalidateToken(token);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/resetPassword")
    public GenericResponse resetPassword(HttpServletRequest request, @RequestBody ResetPasswordDto resetPasswordDto) {
        String userEmail = resetPasswordDto.getEmail();
        Optional<User> userOptional = userService.findUserByEmail(userEmail);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        passwordResetService.createPasswordResetTokenForUser(user, token);
        passwordResetService.sendPasswordResetTokenEmail(getAppUrl(request), request.getLocale(), token, user);
        return new GenericResponse("Reset password email sent successfully");
    }

    @GetMapping("/changePassword")
    public GenericResponse showChangePasswordPage(Locale locale, @RequestParam("token") String token) {
        String result = passwordResetService.validatePasswordResetToken(token);
        if (result != null) {
            return new GenericResponse(messages.getMessage("auth.message." + result, null, locale));
        }
        return new GenericResponse("Valid token");
    }

    @PostMapping("/savePassword")
    public GenericResponse savePassword(final Locale locale, @Valid @RequestBody PasswordDto passwordDto) {
        String result = passwordResetService.validatePasswordResetToken(passwordDto.getToken());

        if (result != null) {
            return new GenericResponse(messages.getMessage("auth.message." + result, null, locale));
        }

        Optional<User> userOptional = passwordResetService.getUserByPasswordResetToken(passwordDto.getToken());
        if (userOptional.isPresent()) {
            passwordResetService.changeUserPassword(userOptional.get(), passwordDto.getNewPassword());
            return new GenericResponse("Password reset successfully");
        } else {
            return new GenericResponse(messages.getMessage("auth.message.invalid", null, locale));
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getUserProfile(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        return userService.getUserDetailsByToken(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
//    @GetMapping
//    public ResponseEntity<Map<String, List<UserResponseDto>>> getAllUsers(@RequestParam(required = false) String role) {
//        List<UserResponseDto> users = userService.getAllUsers().stream()
//                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole()))
//                .toList();
//
//        Map<String, List<UserResponseDto>> groupedUsers = new HashMap<>();
//        groupedUsers.put("Starter", new ArrayList<>());
//        groupedUsers.put("Pro", new ArrayList<>());
//
//        users.forEach(user -> {
//            if ("Starter".equalsIgnoreCase(user.getRole())) {
//                groupedUsers.get("Starter").add(user);
//            } else if ("Pro".equalsIgnoreCase(user.getRole())) {
//                groupedUsers.get("Pro").add(user);
//            }
//        });
//
//        if (role != null && (role.equalsIgnoreCase("Starter") || role.equalsIgnoreCase("Pro"))) {
//            Map<String, List<UserResponseDto>> filteredUsers = new HashMap<>();
//            filteredUsers.put(role, groupedUsers.get(role));
//            return ResponseEntity.ok(filteredUsers);
//        } else {
//            return ResponseEntity.ok(groupedUsers);
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole()))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}