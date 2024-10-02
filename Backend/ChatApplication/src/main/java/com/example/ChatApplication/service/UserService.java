package com.example.ChatApplication.service;

import com.example.ChatApplication.dto.CreateUserRequest;
import com.example.ChatApplication.dto.GetAllUserResponce;
import com.example.ChatApplication.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String name);
    Boolean createUser(CreateUserRequest createUserRequest);
    GetAllUserResponce getAllUsers();
}
