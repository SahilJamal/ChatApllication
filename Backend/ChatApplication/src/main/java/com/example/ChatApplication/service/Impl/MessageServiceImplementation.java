package com.example.ChatApplication.service.Impl;

import com.example.ChatApplication.dto.GetAllMessageRequest;
import com.example.ChatApplication.dto.GetAllMessageResponce;
import com.example.ChatApplication.dto.SendMessageRequest;
import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.model.User;
import com.example.ChatApplication.repository.MessageRepository;
import com.example.ChatApplication.service.MessageService;
import com.example.ChatApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<GetAllMessageResponce> getAllMessages(String senderName, GetAllMessageRequest getAllMessageRequest) {

        User sender = userService.getUserByName(senderName);
        User receiver = userService.getUserByName(getAllMessageRequest.getReceiverName());

        if (sender == null || receiver == null) {
            return List.of();
        }

        // Fetch both sent and received messages
        List<Message> messages = messageRepository.findBySenderAndReceiverOrReceiverAndSender(sender, receiver);

        messages.forEach(message -> {
            System.out.println("Message: " + message.getContent() + " From: " + message.getSender().getUserName() + " To: " + message.getReceiver().getUserName());
        });

        // Map each message to the response DTO, determining if it's "SENT" or "RECEIVED"
        return messages.stream().map(message -> {
            String messageType = message.getSender().getId().equals(sender.getId()) ? "SENT" : "RECEIVED";
            return GetAllMessageResponce.builder()
                    .messageType(messageType)
                    .content(message.getContent())
                    .timestamp(message.getTimestamp())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean sendMessage(String senderName,SendMessageRequest sendMessageRequest) {

        if (sendMessageRequest.getMessage() == null || sendMessageRequest.getMessage().isEmpty()) {
            return false;
        }

        User sender = userService.getUserByName(senderName);
        User receiver = userService.getUserByName(sendMessageRequest.getReceiverName());

        if (sender == null || receiver == null) {
            return false;
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(sendMessageRequest.getMessage()); // Assume you add content in `SendMessageRequest`
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message) != null;
    }
}
