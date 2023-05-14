package com.example.pstravel.service;

import com.example.pstravel.Dto.ChatRoomList;
import com.example.pstravel.Entity.ChatRoomGroup;
import com.example.pstravel.Entity.User;
import com.example.pstravel.Entity.enums.ChatRoomEnum;
import com.example.pstravel.Repository.ChatRoomGroupRepo;
import com.example.pstravel.Repository.UserRepo;
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
    public List<ChatRoomList> getChatRoomList(Long userIdx) {
        Optional<User> userOptional = userRepo.findByUserIdx(userIdx);
        List<ChatRoomGroup> byUserIdAndShowStatus = chatRoomGroupRepo.findByUserIdAndShowStatus(userOptional.get().getUserId(), ChatRoomEnum.SHOW);
        return ChatRoomList.list(byUserIdAndShowStatus);
    }

}
