package com.sideproject.pstravel.Repository.JPA;

import com.sideproject.pstravel.Entity.ChatRoomGroup;
import com.sideproject.pstravel.Entity.enums.ChatRoomEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomGroupRepo extends JpaRepository<ChatRoomGroup,Long> {
    List<ChatRoomGroup> findByUserIdAndShowStatus(String userId, ChatRoomEnum showStatus);
    List<ChatRoomGroup> findByUserId(String userId);
}
