package com.spring_ai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {

    private final ChatModel chatModel;
    private StreamingChatModel streamingChatModel;

    public ChatService(ChatModel chatModel, StreamingChatModel streamingChatModel) {
        this.chatModel = chatModel;
        this.streamingChatModel = streamingChatModel;
    }
    public String generateResponse(String inputText){
        return chatModel.call(inputText);
    }

    public Flux<String> streamResponse(String inputText){
        return streamingChatModel.stream(inputText);
    }
}
