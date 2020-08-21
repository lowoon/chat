package com.example.chat.api.dto

import java.util.*

data class ChatMessage(val messageType: MessageType, val roomId: String, val sender: String, var message: String) {
    enum class MessageType {
        ENTER, TALK;

        fun isEnter(): Boolean {
            return this == ENTER
        }
    }
}

data class ChatRoom(val id: String, val name: String) {
    constructor(name: String) : this(UUID.randomUUID().toString(), name)
}

