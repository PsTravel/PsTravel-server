package com.sideproject.pstravel.Controllter;

import com.sideproject.pstravel.Dto.ChatRoomHistoryDto;
import com.sideproject.pstravel.Dto.MessageDelDto;
import com.sideproject.pstravel.Dto.MessageSaveDto;
import com.sideproject.pstravel.ErrorHandler.ErrorResponse;
import com.sideproject.pstravel.ErrorHandler.MessageDelException;
import com.sideproject.pstravel.service.ChatMessageService;
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
    public ResponseEntity<?> saveMessage(@RequestBody MessageSaveDto messageSaveDto) {
        chatMessageService.saveMessage(messageSaveDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-history")
    public ResponseEntity<?> getHistory(@RequestBody ChatRoomHistoryDto chatRoomHistoryDto) {
        List<ChatRoomHistoryDto> history = chatMessageService.getHistory(chatRoomHistoryDto);

        return ResponseEntity.ok().body(history);
    }

    @PostMapping("/delete-message")
    public ResponseEntity<?> deleteMessage(@RequestBody MessageDelDto messageDelDto) throws MessageDelException {
        try {
            chatMessageService.deleteMessage(messageDelDto);
            return ResponseEntity.ok().build();
        } catch (MessageDelException e) {
            ErrorResponse customError = ErrorResponse.builder()
                    .status(e.getErrorCode().getStatus())
                    .message(e.getErrorCode().getMessage())
                    .code(e.getErrorCode().getErrorCode())
                    .build();
            return ResponseEntity.badRequest().body(customError);
        }
    }
}
