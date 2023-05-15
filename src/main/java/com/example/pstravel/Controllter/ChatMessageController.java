package com.example.pstravel.Controllter;

import com.example.pstravel.Dto.MessageSaveDto;
import com.example.pstravel.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/chat-room")
public class ChatMessageController {
    @Autowired
    ChatMessageService chatMessageService;
    /**
     * @description 유저의 메세지를 저장함.
     */
    @PostMapping("/send-message")
    public ResponseEntity<?> saveMessage(@RequestBody MessageSaveDto messageSaveDto){
        chatMessageService.saveMessage(messageSaveDto);
        return ResponseEntity.ok().build();
    }
}
