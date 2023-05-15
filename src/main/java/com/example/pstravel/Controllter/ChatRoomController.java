package com.example.pstravel.Controllter;

import com.example.pstravel.Dto.CreateRoomGroupDto;
import com.example.pstravel.Entity.ChatRoomGroup;
import com.example.pstravel.service.ChatRoomGroupService;
import com.example.pstravel.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/chat-room")
public class ChatRoomController {

    @Autowired
    ChatRoomGroupService chatRoomGroupService;
    /**
     * @DESCRIPTION 유저끼리의 채팅방 만들기
     */
    @PostMapping("/create-room")
    public void createRoom(@RequestBody CreateRoomGroupDto createRoomGroupDto){
        chatRoomGroupService.createRoom(createRoomGroupDto);
    }
}
