package com.example.chat.api

import com.example.chat.api.dto.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller

@Controller
class ChatController(val messagingTemplate: SimpMessageSendingOperations) {
    @MessageMapping("/chat/message")
    fun message(chatMessage: ChatMessage) {
        if (chatMessage.messageType.isEnter()) {
            chatMessage.message = "${chatMessage.sender}님이 입장하셨습니다."
        }
        messagingTemplate.convertAndSend("/sub/chat/room/${chatMessage.roomId}", chatMessage)
    }
}
