package com.sideproject.pstravel.Controllter;

import com.sideproject.pstravel.Dto.CreateRoomGroupDto;
import com.sideproject.pstravel.service.ChatRoomGroupService;
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
