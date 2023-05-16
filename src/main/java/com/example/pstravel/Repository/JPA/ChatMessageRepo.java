package com.example.pstravel.Repository.JPA;

import com.example.pstravel.Entity.ChatMessage;
import com.example.pstravel.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long > {

    List<ChatMessage> findByChatRoomIdxAndSenderAndRecipientOrderByCreatedAtDesc(ChatRoom chatRoom, String sender, String recipient);

}
