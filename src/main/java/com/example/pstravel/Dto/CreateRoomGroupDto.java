package com.example.pstravel.Dto;

import com.example.pstravel.Entity.ChatRoom;
import com.example.pstravel.Entity.ChatRoomGroup;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CreateRoomGroupDto {

    private String opponent;
    private String userId;
    private ChatRoomEnum chatRoomEnum;
    private ChatRoom chatRoom;

    @Builder
    public CreateRoomGroupDto(String opponent, String userId, ChatRoomEnum chatRoomEnum, ChatRoom chatRoom) {
        this.opponent = opponent;
        this.userId = userId;
        this.chatRoomEnum = chatRoomEnum;
        this.chatRoom = chatRoom;
    }

    public static ChatRoomGroup convertToChatRoomGroup(CreateRoomGroupDto dto) {
        ChatRoomGroup chatRoomGroup = ChatRoomGroup.builder()
                .opponent(dto.getOpponent())
                .userId(dto.getUserId())
                .showStatus(dto.getChatRoomEnum())
                .chatRoomIdx(dto.getChatRoom())
                .build();

        return chatRoomGroup;
    }

}
