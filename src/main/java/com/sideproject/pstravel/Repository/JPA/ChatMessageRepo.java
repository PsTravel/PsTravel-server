package com.sideproject.pstravel.Repository.JPA;

import com.sideproject.pstravel.Entity.ChatMessage;
import com.sideproject.pstravel.Entity.ChatRoom;
import com.sideproject.pstravel.Entity.enums.ChatRoomEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long > {

    List<ChatMessage> findByChatRoomIdxAndSenderAndRecipientAndShowStatusOrderByCreatedAtDesc(ChatRoom chatRoom, String sender, String recipient, ChatRoomEnum chatRoomEnum);

    Optional<ChatMessage> findByChatMessageIdx(Long id);

    @Modifying
    @Query("UPDATE ChatMessage cm SET cm.showStatus = :status WHERE cm.chatMessageIdx = :id AND cm.sender = :userId")
    int updateChatMessageStatus(@Param("id") Long id, @Param("userId") String userId, @Param("status") ChatRoomEnum status);
}

