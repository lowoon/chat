package com.example.chat.service

import com.example.chat.api.dto.ChatRoom
import com.example.chat.common.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.*

@Service
class ChatService(val chatRooms: Map<String, ChatRoom> = mutableMapOf()) {
    fun findAllRooms(): List<ChatRoom> {
        return chatRooms.values.toList()
    }

    fun findRoomById(roomId: String): ChatRoom {
        return chatRooms[roomId] ?: error("존재하지 않는 채팅방입니다.")
    }

    fun createRoom(name: String): ChatRoom {
        val roomId = UUID.randomUUID().toString()
        val chatRoom = ChatRoom(roomId, name)
        chatRooms.plus(Pair(roomId, chatRoom))
        return chatRoom
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        try {
            session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(message)))
        } catch (e: IOException) {
            logger.error(e.message, e)
        }
    }

    companion object : Log
}
