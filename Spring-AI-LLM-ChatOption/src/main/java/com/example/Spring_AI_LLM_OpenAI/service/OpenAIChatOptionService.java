package com.example.Spring_AI_LLM_OpenAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OpenAIChatOptionService {

    private final ChatClient chatClient;

    public OpenAIChatOptionService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    public String askToAI(String message) {
        ChatOptions chatOptions = ChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.3)
                .maxTokens(400)
//                .frequencyPenalty(0.7)
//                .presencePenalty(0.7)
//                .stopSequences(List.of("}"))
//                .topK(50)
//                .topP(0.5)
                .build();
        return chatClient
                .prompt(message)
                .options(chatOptions)
                .call()
                .content();

    }

    public Flux<String> askToAIStream(String message) {
        return chatClient
                .prompt(message)
                .stream()
                .content();

    }
}