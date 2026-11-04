package com.spring_ai.controller;

import com.spring_ai.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<String> generateResponse(@RequestParam(value = "inputText") String inputText){
        String responseText = chatService.generateResponse(inputText);
        return ResponseEntity.ok(responseText);
    }

    @GetMapping("/stream")
    public ResponseEntity<Flux<String>> streamingResponse(@RequestParam(value = "inputText") String inputText){
        Flux<String> streamResponse= chatService.streamResponse(inputText);
        return ResponseEntity.ok(streamResponse);
    }
}
