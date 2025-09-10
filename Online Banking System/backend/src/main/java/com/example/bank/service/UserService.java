package com.example.bank.service;

import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;
import com.example.bank.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authManager, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public String register(String username, String password){
        if (userRepository.existsByUsername(username)) throw new RuntimeException("User exists");
        User u = new User(username, passwordEncoder.encode(password));
        userRepository.save(u);
        return jwtUtil.generateToken(username);
    }

    public String authenticate(String username, String password){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(username);
    }
}
