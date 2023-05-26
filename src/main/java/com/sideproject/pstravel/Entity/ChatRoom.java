package com.sideproject.pstravel.Entity;

import com.sideproject.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class ChatRoom extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomIdx;

    @OneToMany(mappedBy = "chatRoomIdx")
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "chatRoomIdx")
    private List<ChatRoomGroup> chatRoomGroups;

    @Builder
    public ChatRoom(Long chatRoomIdx, List<ChatMessage> chatMessages, List<ChatRoomGroup> chatRoomGroups) {
        this.chatRoomIdx = chatRoomIdx;
        this.chatMessages = chatMessages;
        this.chatRoomGroups = chatRoomGroups;
    }
}
