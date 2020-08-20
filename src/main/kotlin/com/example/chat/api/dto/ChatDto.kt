package com.example.chat.api.dto

import com.example.chat.api.dto.ChatMessage.Companion.from
import com.example.chat.service.ChatService
import org.springframework.web.socket.WebSocketSession

data class ChatMessage(val messageType: MessageType, val roomId: String, val sender: String, val message: String) {
    companion object {
        fun from(chatMessage: ChatMessage, message: String): ChatMessage {
            return ChatMessage(chatMessage.messageType, chatMessage.roomId, chatMessage.sender, message);
        }
    }

    enum class MessageType {
        ENTER, TALK;

        fun isEnter(): Boolean {
            return this == ENTER
        }
    }
}

data class ChatRoom(val roomId: String, val name: String, val sessions: Set<WebSocketSession> = mutableSetOf()) {
    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.messageType.isEnter()) {
            sessions.plus(session)
        }
        sendMessage(from(chatMessage, "${chatMessage.sender}님이 입장하셨습니다."), chatService)
    }

    private fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream()
                .forEach { chatService.sendMessage(it, message) }
    }
}

