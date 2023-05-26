package com.sideproject.pstravel.Dto;

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
