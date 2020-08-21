package com.example.chat.domain

import com.example.chat.api.dto.ChatRoom
import org.springframework.stereotype.Repository

@Repository
class ChatRoomRepository(val chatRooms: Map<String, ChatRoom> = mutableMapOf()) {
    fun findAllRooms(): List<ChatRoom> {
        return chatRooms.values.toList().reversed()
    }

    fun findRoomById(roomId: String): ChatRoom {
        return chatRooms[roomId] ?: error("존재하지 않는 채팅방입니다.")
    }

    fun createChatRoom(name: String): ChatRoom {
        val chatRoom = ChatRoom(name)
        chatRooms.plus(Pair(chatRoom.id, chatRoom))
        return chatRoom
    }
}
