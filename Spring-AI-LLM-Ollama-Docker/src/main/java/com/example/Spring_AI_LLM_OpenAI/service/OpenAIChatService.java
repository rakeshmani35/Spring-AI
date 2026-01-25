package com.example.Spring_AI_LLM_OpenAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatService {

    private final ChatClient chatClient;

    public OpenAIChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    public String chatWithOpenAILLM(String message) {
        System.out.println("Chatting with OpenAI LLM: " + message);
        return chatClient
                .prompt(message)
                .call()
                .content();
    }
}