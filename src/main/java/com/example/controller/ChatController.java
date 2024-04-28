package com.example.controller;

import com.example.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    // {contentName} 동적 변수를 포함하여 메시지를 받을 경로를 설정
    @MessageMapping("/partyroom/chat/{SomeId}/message")
    // 동적 변수를 포함하여 메시지를 브로드캐스트할 경로 설정
    @SendTo("/topic/partyroom/chat/{SomeId}")
    public Message sendMessage(@DestinationVariable String contentName, Message message) {
        // 메시지 처리 로직
        // 메시지에 채팅방 이름이나 ID를 추가하여 구분할 수 있습니다.
        // 예시: message.setChatRoom(contentName);
        return message;
    }
}
