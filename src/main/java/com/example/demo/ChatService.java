package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class ChatService {
    private final WebClient webClient;
    private final String apiUrl;
    private final String apiKey;

    public ChatService(WebClient webClient, String apiUrl, String apiKey) {
        this.webClient = webClient;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public Flux<String> streamChatRequest(ChatRequest request) {
        return webClient.post()
            .uri(apiUrl)
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Bearer " + apiKey)
            .bodyValue(request)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(), (ClientResponse clientResponse) -> {
                return clientResponse.bodyToMono(String.class)
                    .map(errorBody -> {
                        throw new RuntimeException("API请求失败: " + errorBody);
                    });
            })
            .bodyToFlux(String.class)
            .map(data -> "data: " + data + "\n\n"); // 符合SSE协议格式
    }
}

// 假设缺少的ChatRequest类定义
class ChatRequest {
    // 根据实际API需求补充字段和方法
}