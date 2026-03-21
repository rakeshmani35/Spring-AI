package com.example.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class OpenAIChatService {

    private final ChatClient chatClient;

    private final RealtimeDataTool realtimeDataTool;

    public OpenAIChatService(ChatClient chatClient, RealtimeDataTool realtimeDataTool) {
        this.chatClient = chatClient;
        this.realtimeDataTool = realtimeDataTool;
    }


    public String askToAI(String message) {
        return chatClient
                .prompt(message)
                .tools(realtimeDataTool)
                .call()
                .content();
    }

}
