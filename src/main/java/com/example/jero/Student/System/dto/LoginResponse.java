package com.example.jero.Student.System.dto;

import lombok.Getter;

@Getter 
public class LoginResponse {
    private final String message;
    private final String name;
    private final String email;

    public LoginResponse(
            String message,
            String name,
            String email
    ) {
        this.message = message;
        this.name = name;
        this.email = email;
    }
}
