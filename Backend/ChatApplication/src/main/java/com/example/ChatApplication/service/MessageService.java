package com.example.ChatApplication.service;

import com.example.ChatApplication.dto.GetAllMessageRequest;
import com.example.ChatApplication.dto.GetAllMessageResponce;
import com.example.ChatApplication.dto.SendMessageRequest;
import com.example.ChatApplication.model.Message;

import java.util.List;

public interface MessageService {
    List<GetAllMessageResponce> getAllMessages(String senderName,GetAllMessageRequest getAllMessageRequest);
    Boolean sendMessage(String senderName, SendMessageRequest sendMessageRequest);
}
