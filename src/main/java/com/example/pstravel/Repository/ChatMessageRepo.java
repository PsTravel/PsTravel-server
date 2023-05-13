package com.example.pstravel.Repository;

import com.example.pstravel.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long > {
}
