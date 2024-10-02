package com.example.ChatApplication.controller;

import com.example.ChatApplication.dto.CreateUserRequest;
import com.example.ChatApplication.dto.SignInRequest;
import com.example.ChatApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<String> userSignIn(@RequestBody SignInRequest signInRequest){
        try {
            // Create an authentication token with the provided username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword())
            );

            // If authentication is successful, encode the username and password as Basic Auth token
            String credentials = signInRequest.getUserName() + ":" + signInRequest.getPassword();
            String encodedToken = Base64.getEncoder().encodeToString(credentials.getBytes());

            // Return the encoded token in the response
            return ResponseEntity.ok("Basic " + encodedToken);
        } catch (BadCredentialsException e) {
            // Handle invalid login credentials
            return ResponseEntity.status(401).body("Invalid credentials.");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            // Handle any other authentication exceptions
            return ResponseEntity.status(500).body("Authentication failed.");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> userSignUp(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

}
