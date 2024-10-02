package com.example.ChatApplication.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateUserRequest {

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;
}
