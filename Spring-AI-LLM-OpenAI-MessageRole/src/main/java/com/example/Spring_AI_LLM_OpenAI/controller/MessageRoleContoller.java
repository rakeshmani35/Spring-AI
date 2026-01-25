package com.example.Spring_AI_LLM_OpenAI.controller;

import com.example.Spring_AI_LLM_OpenAI.service.MessageRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openapi")
public class MessageRoleContoller {

    private final MessageRoleService messageRoleService;

    public MessageRoleContoller(MessageRoleService messageRoleService) {
        this.messageRoleService = messageRoleService;
    }


    @GetMapping("/v1/check/policy")
    public String checkInsurancePolicyV1(@RequestParam String message){
        return messageRoleService.checkPolicyWithRoleV1(message);
    }


    @GetMapping("/v2/check/policy")
    public String checkInsurancePolicyV2(@RequestParam String message){
        return messageRoleService.checkPolicyWithRoleV2(message);
    }

    @GetMapping("/v3/check/policy")
    public String checkInsurancePolicyV3(@RequestParam String message){
        return messageRoleService.checkPolicyWithRoleV3(message);
    }
}
