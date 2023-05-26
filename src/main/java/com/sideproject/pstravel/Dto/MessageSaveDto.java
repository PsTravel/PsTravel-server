package com.sideproject.pstravel.Dto;

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
