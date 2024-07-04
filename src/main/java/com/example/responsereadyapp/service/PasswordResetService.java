package com.example.responsereadyapp.service;

import com.example.responsereadyapp.entity.PasswordResetToken;
import com.example.responsereadyapp.entity.User;
import com.example.responsereadyapp.repository.PasswordResetTokenRepository;
import com.example.responsereadyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    public void sendPasswordResetTokenEmail(String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/users/updatePassword?token=" + token;
        String message = "Reset your password using the link below:\n" + url;
        mailSender.send(constructEmail("Reset Password", message, user));
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("support@example.com");
        return email;
    }

    public String validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> passTokenOpt = passwordTokenRepository.findByToken(token);

        if (passTokenOpt.isEmpty()) {
            return "invalidToken";
        }

        PasswordResetToken passToken = passTokenOpt.get();
        return isTokenExpired(passToken) ? "expired" : null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public Optional<User> getUserByPasswordResetToken(String token) {
        return passwordTokenRepository.findByToken(token).map(PasswordResetToken::getUser);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }
}
