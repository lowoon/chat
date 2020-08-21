package com.example.chat.api

import com.example.chat.api.dto.ChatRoom
import com.example.chat.domain.ChatRoomRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/chat")
class ChatRoomController(val chatRoomRepository: ChatRoomRepository) {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/rooms")
    @ResponseBody
    fun roomList(): List<ChatRoom> {
        return chatRoomRepository.findAllRooms()
    }

    @PostMapping("/rooms")
    @ResponseBody
    fun createRoom(@RequestParam roomName: String): ChatRoom {
        return chatRoomRepository.createChatRoom(roomName)
    }

    @GetMapping("/{roomId}")
    fun enter(@PathVariable roomId: String): String {
        return "room"
    }

    @GetMapping("/rooms/{roomId}")
    @ResponseBody
    fun room(@PathVariable roomId: String): ChatRoom {
        return chatRoomRepository.findRoomById(roomId)
    }
}
