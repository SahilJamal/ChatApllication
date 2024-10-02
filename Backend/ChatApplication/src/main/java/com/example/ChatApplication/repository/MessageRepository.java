package com.example.ChatApplication.repository;

import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender = :sender AND m.receiver = :receiver) OR (m.sender = :receiver AND m.receiver = :sender) ORDER BY m.timestamp ASC")
    List<Message> findBySenderAndReceiverOrReceiverAndSender(@Param("sender") User sender, @Param("receiver") User receiver);

}
