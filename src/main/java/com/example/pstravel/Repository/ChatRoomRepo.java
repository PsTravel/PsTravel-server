package com.example.pstravel.Repository;

import com.example.pstravel.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepo extends JpaRepository<Long, ChatRoom> {
}
