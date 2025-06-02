package com.uselesswater.warehouse.deepseek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * className: DeepSeekConfig  @date 2025/5/19 16:23  @author UselessWater  @jdk_version 17
 *
 * @description deepseek请求相关配置
 */
@Configuration
public class DeepSeekConfig {

    //配置RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
