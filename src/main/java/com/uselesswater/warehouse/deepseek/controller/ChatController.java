package com.uselesswater.warehouse.deepseek.controller;

import com.uselesswater.warehouse.deepseek.beans.*;
import com.uselesswater.warehouse.deepseek.service.DeepSeekService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private DeepSeekService deepSeekService;

    // 完整请求流程
    @PostMapping
    public ApiResponse<ChatResponse> chat(@RequestBody UserRequest userRequest) {
        // 1. 构建业务请求
        ChatRequest chatRequest = buildChatRequest(userRequest);
        
        // 2. 调用AI服务
        ChatResponse aiResponse = deepSeekService.sendChatRequest(chatRequest);
        
        // 3. 返回标准响应
        return ApiResponse.success(aiResponse);
    }

    private ChatRequest buildChatRequest(UserRequest userRequest) {
        return new ChatRequest.Builder()
            .model("deepseek-chat")
            .addMessage(createSystemPrompt(userRequest))
            .addMessage(new ChatMessage("user", userRequest.getQuestion()))
            .stream(false)
            .temperature(userRequest.isNeedDetail() ? 0.7 : 0.3)
            .build();
    }

    private ChatMessage createSystemPrompt(UserRequest request) {
        String language = StringUtils.isNoneBlank(request.getLanguage())
            ? "用" + request.getLanguage() + "回答"
            : "用中文回答";
            
        return new ChatMessage("system", 
            "你是一个专业的技术顾问。" + language + "，回答需" + 
            (request.isNeedDetail() ? "详细说明" : "简明扼要"));
    }
}