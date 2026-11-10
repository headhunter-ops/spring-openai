package com.spring_ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_ai.payload.CricketResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
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

    public CricketResponse generateCricketResponse(String inputText) throws JsonProcessingException {
        String promptString = "As a cricket expert. Answer this question: " + inputText + ". " +
                "If the above question is not related to cricket." +
                "Generate one joke saying this question is out of context." +
                "Give a JSON response that must contain 'content' as key and your response as value. ";

        ChatResponse cricketResponse = chatModel.call(new Prompt(promptString));

        //get content as string
        String responseString = cricketResponse.getResult().getOutput().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseString, CricketResponse.class);
    }
}
