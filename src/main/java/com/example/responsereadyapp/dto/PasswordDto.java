package com.example.responsereadyapp.dto;

import jakarta.validation.constraints.*;



public class PasswordDto {

    @NotEmpty
    private String token;

    @NotEmpty
    private String newPassword;

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
