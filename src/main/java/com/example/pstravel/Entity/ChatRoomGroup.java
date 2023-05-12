package com.example.pstravel.Entity;

import com.example.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoomGroup extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomGroupIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_idx", referencedColumnName="chatRoomIdx", nullable = false)
    private ChatRoom chatRoomIdx;

    @Column(nullable = false)
    private Boolean showStatus;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String opponent;

}
