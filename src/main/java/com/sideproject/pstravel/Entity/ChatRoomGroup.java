package com.sideproject.pstravel.Entity;

import com.sideproject.pstravel.Entity.enums.ChatRoomEnum;
import com.sideproject.pstravel.Entity.timeTable.TimeTable;
import jakarta.persistence.*;
import lombok.Builder;
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
    @Enumerated(EnumType.STRING)
    private ChatRoomEnum showStatus;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String opponent;


    @Builder
    public ChatRoomGroup(Long chatRoomGroupIdx, ChatRoom chatRoomIdx, ChatRoomEnum showStatus, String userId, String opponent) {
        this.chatRoomGroupIdx = chatRoomGroupIdx;
        this.chatRoomIdx = chatRoomIdx;
        this.showStatus = showStatus;
        this.userId = userId;
        this.opponent = opponent;
    }

}
