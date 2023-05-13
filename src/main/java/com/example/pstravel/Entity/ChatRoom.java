package com.example.pstravel.Entity;

import com.example.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter @NoArgsConstructor
public class ChatRoom  extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomIdx;

    @OneToMany(mappedBy = "chatRoomIdx")
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "chatRoomIdx")
    private List<ChatRoomGroup> chatRoomGroups;
}
