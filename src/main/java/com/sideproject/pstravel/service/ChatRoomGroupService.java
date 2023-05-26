package com.sideproject.pstravel.service;

import com.sideproject.pstravel.Dto.ChatRoomListDto;
import com.sideproject.pstravel.Dto.CreateRoomGroupDto;
import com.sideproject.pstravel.Entity.ChatRoom;
import com.sideproject.pstravel.Entity.ChatRoomGroup;
import com.sideproject.pstravel.Entity.User;
import com.sideproject.pstravel.Entity.enums.ChatRoomEnum;
import com.sideproject.pstravel.Repository.JPA.ChatRoomGroupRepo;
import com.sideproject.pstravel.Repository.JPA.ChatRoomRepo;
import com.sideproject.pstravel.Repository.JPA.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<ChatRoomListDto> getChatRoomList(Long userIdx) {
        Optional<User> userOptional = userRepo.findByUserIdx(userIdx);
        List<ChatRoomGroup> byUserIdAndShowStatus = chatRoomGroupRepo.findByUserIdAndShowStatus(userOptional.get().getUserId(), ChatRoomEnum.SHOW);
        return ChatRoomListDto.list(byUserIdAndShowStatus);
    }

    @Transactional
    public void createRoom(CreateRoomGroupDto createRoomGroupDto){
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
