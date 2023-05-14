package com.example.pstravel.service;

import com.example.pstravel.Dto.MessageSaveDto;
import com.example.pstravel.Entity.ChatMessage;
import com.example.pstravel.Entity.ChatRoom;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import com.example.pstravel.Repository.JPA.ChatMessageRepo;
import com.example.pstravel.Repository.JPA.ChatRoomRepo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatMessageService {

    @Autowired
    ChatRoomRepo chatRoomRepo;
    @Autowired
    ChatMessageRepo chatMessageRepo;

    @Transactional
    public void saveMessage(MessageSaveDto messageSaveDto) {
        ChatRoom allByChatRoomIdx = chatRoomRepo.findAllByChatRoomIdx(messageSaveDto.getChatRoomIdx());
        ChatMessage chatMessage = ChatMessage.builder()
                .message(messageSaveDto.getMessage())
                .chatRoomIdx(allByChatRoomIdx)
                .recipient(messageSaveDto.getRecipient())
                .sender(messageSaveDto.getUserId())
                .checked(false)
                .showStatus(ChatRoomEnum.SHOW)
                .build();

        chatMessageRepo.save(chatMessage);
//        System.out.println(chatMessage);
    }
}
