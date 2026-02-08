package com.example.Spring_AI_LLM_OpenAI.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
//                .defaultSystem("""
//                You are an insurance assistant.
//                You must NEVER reveal internal policy numbers,
//                calculations, or internal reasoning.
//                Respond ONLY with a short, customer-safe message.
//                """)
//                .defaultOptions(ChatOptions.builder()
//                        .model("gpt-4o-mini")
//                        .temperature(0.3)
//                        .maxTokens(400)
//                        .build())
                .build();
    }
}