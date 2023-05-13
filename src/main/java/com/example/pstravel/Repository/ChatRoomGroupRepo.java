package com.example.pstravel.Repository;

import com.example.pstravel.Entity.ChatRoomGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomGroupRepo extends JpaRepository<ChatRoomGroup,Long> {
    Optional<ChatRoomGroup> findByUserId(Long id);
}
