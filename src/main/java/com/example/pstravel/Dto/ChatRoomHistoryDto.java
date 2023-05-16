package com.example.pstravel.Dto;

import com.example.pstravel.Entity.ChatMessage;
import com.example.pstravel.Entity.ChatRoom;
import com.example.pstravel.Entity.ChatRoomGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor
public class ChatRoomHistoryDto {

    private Long chatRoomIdx;
    private String sender;
    private String recipient;
    private String message;
    private Long chatMessageIdx;

    @Builder
    public ChatRoomHistoryDto(Long chatRoomIdx, String sender, String recipient, String message, Long chatMessageIdx) {
        this.chatRoomIdx = chatRoomIdx;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.chatMessageIdx = chatMessageIdx;
    }

    public static ChatRoomHistoryDto of(ChatMessage chatMessage){
        return ChatRoomHistoryDto.builder()
                .chatRoomIdx(chatMessage.getChatRoomIdx().getChatRoomIdx())
                .sender(chatMessage.getSender())
                .recipient(chatMessage.getRecipient())
                .message(chatMessage.getMessage())
                .chatMessageIdx(chatMessage.getChatMessageIdx())
                .build();
    }

    public static List<ChatRoomHistoryDto> list(List<ChatMessage> chatRooms){
        List<ChatRoomHistoryDto> chatRoomHistories = new ArrayList<>();
        for(ChatMessage a : chatRooms){
            chatRoomHistories.add(of(a));
        }
        return chatRoomHistories;
    }

}
