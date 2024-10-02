package com.example.ChatApplication.controller;

import com.example.ChatApplication.dto.GetAllMessageRequest;
import com.example.ChatApplication.dto.GetAllMessageResponce;
import com.example.ChatApplication.dto.GetAllUserResponce;
import com.example.ChatApplication.dto.SendMessageRequest;
import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.service.MessageService;
import com.example.ChatApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<Boolean> sendMessage(@RequestBody SendMessageRequest sendMessageRequest, Authentication authentication) {
        User sender = (User) authentication.getPrincipal();
        System.out.println("Authenticated user: " + sender.getUsername() + " Roles: " + sender.getAuthorities());
        return ResponseEntity.ok(messageService.sendMessage(sender.getUsername(),sendMessageRequest));
    }

    @PostMapping("/allmessages")
    public ResponseEntity<List<GetAllMessageResponce>> getChatHistory(@RequestBody GetAllMessageRequest getAllMessageRequest, Authentication authentication) {
        User sender = (User) authentication.getPrincipal();
        return ResponseEntity.ok(messageService.getAllMessages(sender.getUsername() , getAllMessageRequest));
    }

    @GetMapping(value = "/getallusers" , produces = {"text/plain","application/xml","application/json"} )
    public ResponseEntity<GetAllUserResponce> getallUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
