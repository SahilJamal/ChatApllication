package com.example.ChatApplication.dto;

import com.example.ChatApplication.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllMessageResponce {
    private String messageType; // "SENT" or "RECEIVED"
    private String content;
    private LocalDateTime timestamp;
}
