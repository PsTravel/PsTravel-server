package com.sideproject.pstravel.Controllter;

import com.sideproject.pstravel.Dto.ChatRoomListDto;
import com.sideproject.pstravel.service.ChatRoomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/chat-room")
public class ChatRoomGroupController {
    @Autowired
    ChatRoomGroupService chatRoomGroupService;

    @GetMapping("/list")
    public ResponseEntity<?> chatRoomList(@RequestParam Long userIdx){
        List<ChatRoomListDto> chatRoomListDto = chatRoomGroupService.getChatRoomList(userIdx);

        return ResponseEntity.ok().body(chatRoomListDto);
    }
}
