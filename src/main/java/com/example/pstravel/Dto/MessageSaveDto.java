package com.example.pstravel.Dto;

import com.example.pstravel.Entity.ChatMessage;
import com.example.pstravel.Entity.ChatRoom;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageSaveDto {
    private Long chatRoomIdx;
    private String userId;
    private String recipient;
    private String message;

    @Builder
    public MessageSaveDto(Long chatRoomIdx, String userId, String recipient, String message) {
        this.chatRoomIdx = chatRoomIdx;
        this.userId = userId;
        this.recipient = recipient;
        this.message = message;
    }
}
