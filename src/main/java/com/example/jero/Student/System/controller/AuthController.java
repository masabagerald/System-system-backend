package com.example.jero.Student.System.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jero.Student.System.dto.LoginRequest;
import com.example.jero.Student.System.dto.LoginResponse;
import com.example.jero.Student.System.dto.RegisterRequest;
import com.example.jero.Student.System.model.User;
import com.example.jero.Student.System.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody RegisterRequest request
    ) {

        User user = authService.register(request);

        LoginResponse response = new LoginResponse(
                "User registered successfully",
                user.getName(),
                user.getEmail()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        User user = authService.authenticate(request);

        LoginResponse response = new LoginResponse(
                "Login successful",
                user.getName(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }
}