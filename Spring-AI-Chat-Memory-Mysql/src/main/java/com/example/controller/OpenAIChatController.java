package com.example.controller;

import com.example.service.OpenAIChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OpenAIChatController {


    private final OpenAIChatService openAIChatService;

    public OpenAIChatController(OpenAIChatService openAIChatService) {
        this.openAIChatService = openAIChatService;
    }


    @GetMapping("/chat")
    public String chat(@RequestParam String message,@RequestHeader("username") String username) {
        return openAIChatService.askToAI(message,username);
    }


}
