package com.example.pstravel.Entity;

import com.example.pstravel.Entity.enums.ChatRoomEnum;
import jakarta.persistence.*;

@Entity
public class ChatMessage {

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
    private Enum<ChatRoomEnum> showStatus;

}
