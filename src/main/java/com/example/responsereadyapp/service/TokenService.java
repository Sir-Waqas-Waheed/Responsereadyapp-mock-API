package com.example.responsereadyapp.service;

import com.example.responsereadyapp.entity.Token;
import com.example.responsereadyapp.entity.User;
import com.example.responsereadyapp.repository.TokenRepository;
import com.example.responsereadyapp.repository.UserRepository;
import com.example.responsereadyapp.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository, JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    public Token saveToken(String jwt, String username) {
        Token token = new Token();
        token.setToken(jwt);
        token.setExpiryDate(jwtTokenUtil.extractExpiration(jwt));
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        token.setUser(user);
        return tokenRepository.save(token);
    }

    public void invalidateToken(String jwt) {
        tokenRepository.deleteByToken(jwt);
    }
}
