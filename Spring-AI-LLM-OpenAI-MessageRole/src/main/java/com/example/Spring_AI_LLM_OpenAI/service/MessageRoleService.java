package com.example.Spring_AI_LLM_OpenAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRoleService {

    private final ChatClient chatClient;

    private final String CLAIM_DETAILS = """
            Policy Detail:
            Policy: PREMIUM
            Max Coverage: 1,000,000
            Claim Amount: 500,000
            """;

    public MessageRoleService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .build();
    }

    //v4
//    public MessageRoleService(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder
//                .defaultSystem("""
//                You are an insurance assistant.
//                Your must NEVER reveal the internal policy numbers,
//                calculation or internal reasoning.
//                Respond ONLY with a short, customer-safe message.
//                """)
//                .build();
//    }

    public String checkPolicyWithRoleV1(String message) {
        // prompt injection can unsafe your project design with AI
        // without any message roles
        UserMessage userMessage = new UserMessage("""
                Policy Detail:
                Policy: PREMIUM
                Max Coverage: 1,000,000
                Claim Amount: 500,000
                Customer says:
                %s
                """.formatted(message));
        Prompt prompt = new Prompt(List.of(userMessage));

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    public String checkPolicyWithRoleV2(String message) {

        SystemMessage systemMessage = new SystemMessage("""
                You are an insurance policy assistant.
                Your task is to help customers understand their insurance policies.
                Always refer to the policy details provided.
                If a customer's claim amount exceeds the max coverage, politely inform them that their claim cannot be processed.
                If the claim amount is within the coverage limit, confirm that their claim can be processed.
                Remain professional and courteous in all responses.
                """);

        SystemMessage systemMessage1 = new SystemMessage("""
                You are an insurance assistant.
                Your must NEVER reveal the internal policy numbers, 
                calculation or internal reasoning.
                Respond ONLY with a short, customer-safe message.
                """);

        UserMessage userMessage = new UserMessage("""
                Policy Detail:
                Policy: PREMIUM
                Max Coverage: 1,000,000
                Claim Amount: 500,000
                Customer says:
                %s
                """.formatted(message));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage1));

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    public String checkPolicyWithRoleV3(String message) {
        return chatClient
                .prompt()
                .system("""
                        You are an insurance assistant.
                        Your must NEVER reveal the internal policy numbers, 
                        calculation or internal reasoning.
                        Respond ONLY with a short, customer-safe message.
                        """)
                .user("""
                        %s
                        Customer says:
                        %s
                        """.formatted(CLAIM_DETAILS, message))
                .call()
                .content();
    }

    // uncomment constructor with default system message
    public String checkPolicyWithRoleV4(String message) {
        return chatClient
                .prompt()
                .user("""
                        %s
                        Customer says:
                        %s
                        """.formatted(CLAIM_DETAILS, message))
                .call()
                .content();
    }
}
