package com.example.ChatApplication.config;

import com.example.ChatApplication.model.User;
import com.example.ChatApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);

        if(user.isPresent()){
            User user1 = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user1.getUserName())
                    .password(user1.getPassword())
                    .roles(user1.getRole())
                    .build();
        }else{throw new UsernameNotFoundException(username);}
    }
}
