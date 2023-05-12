package com.example.pstravel.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoomGroup extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_idx")
    @Column(nullable = false)
    private ChatRoom chatRoomIdx;
    @Column(nullable = false)
    private Boolean show;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String opponent;

}
