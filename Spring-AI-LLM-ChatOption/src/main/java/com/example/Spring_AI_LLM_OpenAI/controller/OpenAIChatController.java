package com.example.Spring_AI_LLM_OpenAI.controller;

import com.example.Spring_AI_LLM_OpenAI.service.OpenAIChatOptionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/openai/api")
public class OpenAIChatController {


    private final OpenAIChatOptionService openAIChatService;

    public OpenAIChatController(OpenAIChatOptionService openAIChatService) {
        this.openAIChatService = openAIChatService;
    }


    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return openAIChatService.askToAI(message);
    }

    @GetMapping(value = "/chat/stream")
    public Flux<String> chatStream(@RequestParam String message) {
        return openAIChatService.askToAIStream(message);
    }

    @GetMapping(value = "/chat/stream/ui", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStreamForUI(@RequestParam String message) {
        return openAIChatService.askToAIStream(message);
    }
}