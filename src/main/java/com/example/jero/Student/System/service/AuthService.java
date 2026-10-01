package com.example.jero.Student.System.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jero.Student.System.dto.LoginRequest;
import com.example.jero.Student.System.dto.RegisterRequest;
import com.example.jero.Student.System.model.User;
import com.example.jero.Student.System.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "A user with this email already exists"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Hash the password before storing it.
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setEnabled(true);

        return userRepository.save(user);
    }

    public User authenticate(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        )
                );

        if (!user.isEnabled()) {
            throw new RuntimeException(
                    "User account is disabled"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        return user;
    }
}