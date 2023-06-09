package com.sideproject.pstravel.service;

import com.sideproject.pstravel.Dto.ChatRoomLastMessage;
import com.sideproject.pstravel.Dto.ChatRoomListDto;
import com.sideproject.pstravel.Dto.CreateRoomGroupDto;
import com.sideproject.pstravel.Entity.ChatMessage;
import com.sideproject.pstravel.Entity.ChatRoom;
import com.sideproject.pstravel.Entity.ChatRoomGroup;
import com.sideproject.pstravel.Entity.User;
import com.sideproject.pstravel.Entity.enums.ChatRoomEnum;
import com.sideproject.pstravel.Repository.JPA.ChatMessageRepo;
import com.sideproject.pstravel.Repository.JPA.ChatRoomGroupRepo;
import com.sideproject.pstravel.Repository.JPA.ChatRoomRepo;
import com.sideproject.pstravel.Repository.JPA.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomGroupService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ChatRoomGroupRepo chatRoomGroupRepo;
    @Autowired
    ChatRoomRepo chatRoomRepo;
    @Autowired
    ChatMessageRepo chatMessageRepo;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<ChatRoomListDto> getChatRoomList(Long userIdx) {
        Optional<User> userOptional = userRepo.findByUserIdx(userIdx);
        List<ChatRoomGroup> byUserIdx = chatRoomGroupRepo.findByUserIdAndShowStatus(userOptional.get().getUserId(), ChatRoomEnum.SHOW);
        List<Long> roomList = new ArrayList<>();
        for(int i=0; i<byUserIdx.size(); i++){
            roomList.add(byUserIdx.get(i).getChatRoomIdx().getChatRoomIdx());
        }
//        String jpql = "SELECT a, b.chatMessages FROM ChatRoomGroup a JOIN a.chatRoomIdx b join b.chatMessages c WHERE a.userId = :userId and a.showStatus = :showStatus ORDER BY c.createdAt DESC";
        String jpql = "SELECT cm FROM ChatMessage cm JOIN cm.chatRoomIdx cr " +
                "where cr.chatRoomIdx in :chatRoomIdxList " +
                "and cm.chatMessageIdx = (SELECT MAX(cmsub.chatMessageIdx) FROM ChatMessage cmsub WHERE cmsub.chatRoomIdx.chatRoomIdx = cr.chatRoomIdx) ";

        TypedQuery<ChatMessage> query = entityManager.createQuery(jpql,  ChatMessage.class);
        query.setParameter("chatRoomIdxList", roomList);
        List<ChatMessage> resultList = query.getResultList();

        List<ChatRoomListDto> chatRoomLastMessages = new ArrayList<>();

        for (ChatMessage result : resultList) {
            chatRoomLastMessages.add(ChatRoomListDto.builder()
                    .lastMessage(result.getMessage())
                    .chatRoomIdx(result.getChatRoomIdx().getChatRoomIdx())
                    .modifiedAt(result.getModifiedAt())
                    .opponent(result.getRecipient())
                    .build());
        }

        return chatRoomLastMessages;
    }

    @Transactional
    public void getLastMessage(List<ChatRoomLastMessage> a , Long roomNumber) {
        //메세지들중에 roomNumber로 찾아서 넘겨
        Optional<ChatMessage> top1ByChatMessageIdxAndShowStatus = chatMessageRepo.findTop1ByChatMessageIdxAndShowStatus(roomNumber,  ChatRoomEnum.SHOW);
        a.add(ChatRoomLastMessage.builder()
                .chatRoomIdx(roomNumber)
                .lastMessage(top1ByChatMessageIdxAndShowStatus.get().getMessage()).build());

    }

    @Transactional
    public void createRoom(CreateRoomGroupDto createRoomGroupDto) {
        /**
         * @우선chatRoom하나생성하고해당인덱스로Group만들기
         */
        // 방 생성하기
        ChatRoom chatRoom = new ChatRoom();
        ChatRoom save = chatRoomRepo.save(chatRoom);
        //그룹 생성하기
        System.out.println(save.getChatRoomIdx());

        CreateRoomGroupDto createRoomGroupDto1 = CreateRoomGroupDto.builder()
                .chatRoomEnum(ChatRoomEnum.SHOW)
                .chatRoom(save)
                .userId(createRoomGroupDto.getUserId())
                .opponent(createRoomGroupDto.getOpponent())
                .build();
        CreateRoomGroupDto createRoomGroupDto2 = CreateRoomGroupDto.builder()
                .chatRoomEnum(ChatRoomEnum.SHOW)
                .chatRoom(save)
                .opponent(createRoomGroupDto.getUserId())
                .userId(createRoomGroupDto.getOpponent())
                .build();

        chatRoomGroupRepo.save(CreateRoomGroupDto.convertToChatRoomGroup(createRoomGroupDto1));
        chatRoomGroupRepo.save(CreateRoomGroupDto.convertToChatRoomGroup(createRoomGroupDto2));
    }

}
