package com.example.pstravel.Dto;

import com.example.pstravel.Entity.ChatRoomGroup;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor @JsonSerialize
public class ChatRoomList {
    private String opponent;
    private Long chatRoomIdx;
    private LocalDateTime modifiedAt;

    public static ChatRoomList of(ChatRoomGroup chatRoomGroup){
        return ChatRoomList.builder()
                .opponent(chatRoomGroup.getOpponent())
                .chatRoomIdx(chatRoomGroup.getChatRoomIdx().getChatRoomIdx())
                .modifiedAt(chatRoomGroup.getModifiedAt())
                .build();
    }

    public static List<ChatRoomList> list(List<ChatRoomGroup> chatRooms){
        List<ChatRoomList> chatRoomHistories = new ArrayList<>();
        for(ChatRoomGroup a : chatRooms){
            chatRoomHistories.add(of(a));
        }
        return chatRoomHistories;
    }
}
