package com.example.pstravel.Entity;

import com.example.pstravel.Entity.enums.ChatRoomEnum;
import com.example.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor
public class ChatMessage extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_idx", referencedColumnName="chatRoomIdx", nullable = false)
    private ChatRoom chatRoomIdx;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private Boolean checked;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatRoomEnum showStatus;

}
