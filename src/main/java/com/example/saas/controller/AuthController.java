package com.example.saas.controller;

import com.example.saas.dto.AuthRequest;
import com.example.saas.dto.AuthResponse;
import com.example.saas.dto.RegisterRequest;
import com.example.saas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse res = authService.register(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse res = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(res);
    }
}
