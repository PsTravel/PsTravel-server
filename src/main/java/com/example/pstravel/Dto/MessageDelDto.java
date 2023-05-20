package com.example.pstravel.Dto;

import com.example.pstravel.Entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class MessageDelDto {

    private String userId;
    private Long chatMessageIdx;

    @Builder
    public MessageDelDto(String userId, Long chatMessageIdx) {
        this.userId = userId;
        this.chatMessageIdx = chatMessageIdx;
    }
}
