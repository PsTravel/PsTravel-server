package com.sideproject.pstravel.service;


import com.sideproject.pstravel.Dto.ChatRoomHistoryDto;
import com.sideproject.pstravel.Dto.MessageDelDto;
import com.sideproject.pstravel.Dto.MessageSaveDto;
import com.sideproject.pstravel.Entity.ChatMessage;
import com.sideproject.pstravel.Entity.ChatRoom;
import com.sideproject.pstravel.Entity.ChatRoomGroup;
import com.sideproject.pstravel.Entity.enums.ChatRoomEnum;
import com.sideproject.pstravel.ErrorHandler.ErrorCode;
import com.sideproject.pstravel.ErrorHandler.MessageDelException;
import com.sideproject.pstravel.Repository.JPA.ChatMessageRepo;
import com.sideproject.pstravel.Repository.JPA.ChatRoomRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
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
    @PersistenceContext
    private EntityManager entityManager;


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
    }

    @Transactional
    public List<ChatRoomHistoryDto> getHistory(ChatRoomHistoryDto chatRoomHistoryDto) {
        try {
            ChatRoom allByChatRoomIdx = chatRoomRepo.findAllByChatRoomIdx(chatRoomHistoryDto.getChatRoomIdx());

            String sender = chatRoomHistoryDto.getSender();
            String recipient = chatRoomHistoryDto.getRecipient();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ChatMessage> query = cb.createQuery(ChatMessage.class);
            Root<ChatMessage> root = query.from(ChatMessage.class);

            Expression<Long> chatRoomIdx = root.get("chatRoomIdx").as(Long.class);
            Expression<String> conRecipient = root.get("recipient").as(String.class);
            Expression<String> conSender = root.get("sender").as(String.class);
            Expression<ChatRoomEnum> conShowStatus = root.get("showStatus");
            // 조건 설정
            Predicate condition1 = cb.and(
                    cb.equal(chatRoomIdx , allByChatRoomIdx.getChatRoomIdx()),
                    cb.equal(conRecipient, sender),
                    cb.equal(conSender, recipient),
                    cb.equal(conShowStatus, ChatRoomEnum.SHOW)
            );

            Predicate condition2 = cb.and(
                    cb.equal(chatRoomIdx , allByChatRoomIdx.getChatRoomIdx()),
                    cb.equal(conRecipient, recipient),
                    cb.equal(conSender, sender),
                    cb.equal(conShowStatus, ChatRoomEnum.SHOW)
            );

            Predicate finalCondition = cb.or(condition1, condition2);

            query.select(root).where(finalCondition);
            query.orderBy(cb.asc(root.get("createdAt")));
            TypedQuery<ChatMessage> typedQuery = entityManager.createQuery(query);
            return ChatRoomHistoryDto.list(typedQuery.getResultList());
        } catch (Exception e) {
            log.error("에러 발생", e);
            throw new RuntimeException("채팅 히스토리 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional
    public void deleteMessage(MessageDelDto messageDelDto) {
        if (chatMessageRepo.findByChatMessageIdx(messageDelDto.getChatMessageIdx()).isPresent()) {
            int i = chatMessageRepo.updateChatMessageStatus(messageDelDto.getChatMessageIdx(), messageDelDto.getUserId(), ChatRoomEnum.DELETE);
            if (i != 1) {
                throw new MessageDelException("message is not exist.", ErrorCode.NOT_EXIST_MESSAGE);
            }
        } else {
            throw new MessageDelException("message is not exist.", ErrorCode.NOT_EXIST_MESSAGE);
        }
    }
}
