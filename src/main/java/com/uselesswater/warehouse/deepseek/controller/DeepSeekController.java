package com.uselesswater.warehouse.deepseek.controller;

import com.uselesswater.warehouse.deepseek.beans.ChatRequest;
import com.uselesswater.warehouse.deepseek.service.DeepSeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class DeepSeekController {
    @Autowired
    private DeepSeekService deepSeekService;

    @PostMapping(value = "/stream-chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestBody ChatRequest request) {
        return deepSeekService.streamChatRequest(request)
                .doOnError(throwable -> {
                    // 记录错误日志
                    log.error("流式处理异常:", throwable);
                });
    }

}