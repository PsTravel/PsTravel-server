package com.example.pstravel.service;

import com.example.pstravel.Dto.ChatRoomListDto;
import com.example.pstravel.Entity.ChatRoomGroup;
import com.example.pstravel.Entity.User;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import com.example.pstravel.Repository.JPA.ChatRoomGroupRepo;
import com.example.pstravel.Repository.JPA.UserRepo;
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

    @Transactional
    public List<ChatRoomListDto> getChatRoomList(Long userIdx) {
        Optional<User> userOptional = userRepo.findByUserIdx(userIdx);
        List<ChatRoomGroup> byUserIdAndShowStatus = chatRoomGroupRepo.findByUserIdAndShowStatus(userOptional.get().getUserId(), ChatRoomEnum.SHOW);
        return ChatRoomListDto.list(byUserIdAndShowStatus);
    }

}
