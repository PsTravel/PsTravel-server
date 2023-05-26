package com.sideproject.pstravel.Dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sideproject.pstravel.Entity.ChatRoomGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor @JsonSerialize
public class ChatRoomListDto {
    private String opponent;
    private Long chatRoomIdx;
    private LocalDateTime modifiedAt;

    public static ChatRoomListDto of(ChatRoomGroup chatRoomGroup){
        return ChatRoomListDto.builder()
                .opponent(chatRoomGroup.getOpponent())
                .chatRoomIdx(chatRoomGroup.getChatRoomIdx().getChatRoomIdx())
                .modifiedAt(chatRoomGroup.getModifiedAt())
                .build();
    }

    public static List<ChatRoomListDto> list(List<ChatRoomGroup> chatRooms){
        List<ChatRoomListDto> chatRoomHistories = new ArrayList<>();
        for(ChatRoomGroup a : chatRooms){
            chatRoomHistories.add(of(a));
        }
        return chatRoomHistories;
    }
}
