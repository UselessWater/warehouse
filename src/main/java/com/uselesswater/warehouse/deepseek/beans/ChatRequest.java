package com.uselesswater.warehouse.deepseek.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    // Getter 方法
    private String model;
    private List<ChatMessage> messages;
    private boolean stream;
    private Double temperature;

    // 私有构造方法
    private ChatRequest(Builder builder) {
        this.model = builder.model;
        this.messages = Collections.unmodifiableList(builder.messages);
        this.stream = builder.stream;
        this.temperature = builder.temperature;
    }

    // Builder 类
    public static class Builder {
        private String model;
        private List<ChatMessage> messages = new ArrayList<>();
        private boolean stream;
        private Double temperature;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder addMessage(ChatMessage message) {
            this.messages.add(message);
            return this;
        }

        public Builder stream(boolean stream) {
            this.stream = stream;
            return this;
        }

        public Builder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        public ChatRequest build() {
            if (model == null || model.isEmpty()) {
                throw new IllegalArgumentException("Model must be specified");
            }
            if (messages.isEmpty()) {
                throw new IllegalArgumentException("At least one message required");
            }
            return new ChatRequest(this);
        }
    }

}