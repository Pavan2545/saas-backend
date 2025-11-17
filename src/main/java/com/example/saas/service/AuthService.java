package com.example.saas.service;

import com.example.saas.dto.AuthResponse;
import com.example.saas.dto.RegisterRequest;
import com.example.saas.model.Role;
import com.example.saas.model.User;
import com.example.saas.repository.UserRepository;
import com.example.saas.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User u = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getFullName());
        Set<Role> roles = new HashSet<>();
        if (request.getRole() == null) {
            roles.add(Role.ROLE_STUDENT);
        } else {
            roles.add(Role.valueOf(request.getRole()));
        }
        u.setRoles(roles);
        userRepository.save(u);
        String token = jwtUtil.generateToken(u.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(String username, String password) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtUtil.generateToken(username);
        return new AuthResponse(token);
    }
}
