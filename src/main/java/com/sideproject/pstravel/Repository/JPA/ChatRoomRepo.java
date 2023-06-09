package com.sideproject.pstravel.Repository.JPA;

import com.sideproject.pstravel.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {
    ChatRoom findAllByChatRoomIdx(Long chatRoomIdx);
}
