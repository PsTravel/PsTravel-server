package com.example.pstravel.service;

import com.example.pstravel.Dto.ChatRoomHistoryDto;
import com.example.pstravel.Dto.MessageDelDto;
import com.example.pstravel.Dto.MessageSaveDto;
import com.example.pstravel.Entity.ChatMessage;
import com.example.pstravel.Entity.ChatRoom;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import com.example.pstravel.ErrorHandler.ErrorCode;
import com.example.pstravel.ErrorHandler.MessageDelException;
import com.example.pstravel.Repository.JPA.ChatMessageRepo;
import com.example.pstravel.Repository.JPA.ChatRoomRepo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
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
        try {
            ChatRoom allByChatRoomIdx = chatRoomRepo.findAllByChatRoomIdx(chatRoomHistoryDto.getChatRoomIdx());

            String sender = chatRoomHistoryDto.getSender();
            String recipient = chatRoomHistoryDto.getRecipient();
            List<ChatMessage> messageHistory = chatMessageRepo.findByChatRoomIdxAndSenderAndRecipientAndShowStatusOrderByCreatedAtDesc(allByChatRoomIdx, sender, recipient, ChatRoomEnum.SHOW);
            List<ChatRoomHistoryDto> result = ChatRoomHistoryDto.list(messageHistory);
            return result;
        } catch (Exception e) {
            log.error("에러 발생", e);
        }
        return null;
    }

    @Transactional
    public void deleteMessage(MessageDelDto messageDelDto) {
        if (chatMessageRepo.findByChatMessageIdx(messageDelDto.getChatMessageIdx()).isPresent()) {
            int i = chatMessageRepo.updateChatMessageStatus(messageDelDto.getChatMessageIdx(), messageDelDto.getUserId(), ChatRoomEnum.DELETE);
            if(i != 1){
                throw new MessageDelException("message is not exist.", ErrorCode.NOT_EXIST_MESSAGE);
            }
        } else {
            throw new MessageDelException("message is not exist.", ErrorCode.NOT_EXIST_MESSAGE);
        }
    }
}
