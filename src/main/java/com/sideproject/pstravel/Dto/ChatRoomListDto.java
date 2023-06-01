package com.sideproject.pstravel.Dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sideproject.pstravel.Entity.ChatRoomGroup;
import com.sideproject.pstravel.service.ChatRoomGroupService;
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
    private String lastMessage;

    public static ChatRoomListDto of(ChatRoomGroup chatRoomGroup, String lastMessages){
        return ChatRoomListDto.builder()
                .opponent(chatRoomGroup.getOpponent())
                .chatRoomIdx(chatRoomGroup.getChatRoomIdx().getChatRoomIdx())
                .modifiedAt(chatRoomGroup.getModifiedAt())
                .lastMessage(lastMessages)
                .build();
    }

    public static List<ChatRoomListDto> list(List<ChatRoomGroup> chatRooms, List<ChatRoomLastMessage> lastMessages){
        List<ChatRoomListDto> chatRoomHistories = new ArrayList<>();
        for(ChatRoomGroup a : chatRooms){
            for(ChatRoomLastMessage b : lastMessages) {
                if(a.getChatRoomIdx().getChatRoomIdx().equals(b.getChatRoomIdx()))
                chatRoomHistories.add(of(a,b.getLastMessage()));
            }
        }
        return chatRoomHistories;
    }
}
