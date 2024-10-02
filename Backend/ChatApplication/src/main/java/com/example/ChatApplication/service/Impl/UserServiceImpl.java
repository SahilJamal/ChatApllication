package com.example.ChatApplication.service.Impl;

import com.example.ChatApplication.dto.CreateUserRequest;
import com.example.ChatApplication.dto.GetAllUserResponce;
import com.example.ChatApplication.model.User;
import com.example.ChatApplication.repository.UserRepository;
import com.example.ChatApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public GetAllUserResponce getAllUsers() {
        List<String> names =  userRepository.findAll().stream().map(user -> user.getUserName()).toList();
        return GetAllUserResponce.builder()
                .name(names)
                .build();
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUserName(name).orElseThrow(()->new UsernameNotFoundException(name));
    }

    @Override
    public Boolean createUser(CreateUserRequest createUserRequest) {
        return userRepository.save(this.mapRequestToUser(createUserRequest)) != null;
    }

    private User mapRequestToUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setUserName(createUserRequest.getUserName());
        user.setEmail(createUserRequest.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        return user;
    }
}
