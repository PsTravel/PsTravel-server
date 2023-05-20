package com.example.pstravel.service;

import com.example.pstravel.Dto.ChatRoomHistoryDto;
import com.example.pstravel.Dto.MessageDelDto;
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

import java.util.List;

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

    @Transactional
    public List<ChatRoomHistoryDto> getHistory(ChatRoomHistoryDto chatRoomHistoryDto) {

        ChatRoom allByChatRoomIdx = chatRoomRepo.findAllByChatRoomIdx(chatRoomHistoryDto.getChatRoomIdx());

        String sender = chatRoomHistoryDto.getSender();
        String recipient = chatRoomHistoryDto.getRecipient();
        List<ChatMessage> messageHistory = chatMessageRepo.findByChatRoomIdxAndSenderAndRecipientOrderByCreatedAtDesc(allByChatRoomIdx, sender, recipient);
        List<ChatRoomHistoryDto> result = ChatRoomHistoryDto.list(messageHistory);
        return result;
    }

    @Transactional
    public void deleteMessage(MessageDelDto messageDelDto) {
        try {
            chatMessageRepo.updateChatMessageStatus(messageDelDto.getChatMessageIdx(), messageDelDto.getUserId(), ChatRoomEnum.DELETE);

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
