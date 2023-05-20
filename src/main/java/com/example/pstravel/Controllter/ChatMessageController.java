package com.example.pstravel.Controllter;

import com.example.pstravel.Dto.ChatRoomHistoryDto;
import com.example.pstravel.Dto.MessageDelDto;
import com.example.pstravel.Dto.MessageSaveDto;
import com.example.pstravel.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-history")
    public ResponseEntity<?> getHistory(@RequestBody ChatRoomHistoryDto chatRoomHistoryDto){
        List<ChatRoomHistoryDto> history = chatMessageService.getHistory(chatRoomHistoryDto);

        return ResponseEntity.ok().body(history);
    }

    @PostMapping("/delete-message")
    public ResponseEntity<?> deleteMessage(@RequestBody MessageDelDto messageDelDto){
        chatMessageService.deleteMessage(messageDelDto);

        return ResponseEntity.ok().build();
    }
}
