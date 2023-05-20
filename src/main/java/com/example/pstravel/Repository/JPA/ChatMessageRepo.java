package com.example.pstravel.Repository.JPA;

import com.example.pstravel.Entity.ChatMessage;
import com.example.pstravel.Entity.ChatRoom;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long > {

    List<ChatMessage> findByChatRoomIdxAndSenderAndRecipientOrderByCreatedAtDesc(ChatRoom chatRoom, String sender, String recipient);

    ChatMessage findByChatMessageIdx(Long id);

    @Modifying
    @Query("UPDATE ChatMessage cm SET cm.showStatus = :status WHERE cm.chatMessageIdx = :id AND cm.sender = :userId")
    void updateChatMessageStatus(@Param("id") Long id, @Param("userId") String userId, @Param("status") ChatRoomEnum status);
}

