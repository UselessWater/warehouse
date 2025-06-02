package com.uselesswater.warehouse.deepseek.service;

import com.uselesswater.warehouse.deepseek.beans.ChatRequest;
import com.uselesswater.warehouse.deepseek.beans.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Service
public class DeepSeekService {
    private static final Logger logger = LoggerFactory.getLogger(DeepSeekService.class);

    @Value("${deepseek.api.url}")
    private String apiUrl;

    @Value("${deepseek.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    public ChatResponse sendChatRequest(ChatRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ChatResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    entity,
                    ChatResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.error("API request failed with status: {}", response.getStatusCode());
                throw new RuntimeException("API request failed");
            }
        } catch (RestClientException e) {
            logger.error("API communication error", e);
            throw new RuntimeException("API communication error", e);
        }
    }

    public Flux<String> streamChatRequest(ChatRequest request) {

        // 符合SSE协议格式
        return webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(String.class)
                .map(data -> "data: " + data + "\n\n");
    }

}