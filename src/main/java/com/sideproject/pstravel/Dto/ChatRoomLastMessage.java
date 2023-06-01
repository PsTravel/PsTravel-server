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
public class ChatRoomLastMessage {
    private Long chatRoomIdx;
    private String lastMessage;
}
