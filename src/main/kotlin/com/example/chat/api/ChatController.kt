package com.example.chat.api

import com.example.chat.api.dto.ChatRoom
import com.example.chat.service.ChatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(val chatService: ChatService) {
    @PostMapping
    fun createRoom(@RequestParam name: String): ChatRoom {
        return chatService.createRoom(name)
    }

    @GetMapping
    fun findAllRooms(): List<ChatRoom> {
        return chatService.findAllRooms()
    }
}
