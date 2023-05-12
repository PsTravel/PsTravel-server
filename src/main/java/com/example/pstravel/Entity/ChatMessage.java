package com.example.pstravel.Entity;

import jakarta.persistence.*;

public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_idx")
    private ChatRoom chatRoom;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private Boolean read;

    @Column(nullable = false)
    private Enum<ChatRoomEnum> show;

}
