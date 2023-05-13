package com.example.pstravel.Repository;

import com.example.pstravel.Entity.ChatRoomGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomGroupRepo extends JpaRepository<Long, ChatRoomGroup> {
}
