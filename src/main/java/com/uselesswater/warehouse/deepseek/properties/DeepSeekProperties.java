package com.uselesswater.warehouse.deepseek.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * className: DeepSeek  @date 2025/5/19 15:23  @author UselessWater  @jdk_version 17
 *
 * @description deepSeek相关请求实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "deep-seek")
@Component
public class DeepSeekProperties {

    private  String apiKey;
    private String baseUrl;//https://api.deepseek.com
    private String model;//deepseek-reasoner 表示R1模型，deepseek-chat 表示V3模型
    private boolean stream;

}
