package com.example.chat.config

import com.example.chat.api.dto.ChatMessage
import com.example.chat.common.Log
import com.example.chat.service.ChatService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler(val chatService: ChatService) : TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        logger.info("payload {}", payload)
        val chatMessage = jacksonObjectMapper().readValue<ChatMessage>(payload)
        val chatRoom = chatService.findRoomById(chatMessage.roomId)
        chatRoom.handleActions(session, chatMessage, chatService)
    }

    companion object : Log
}
