package com.uselesswater.warehouse.deepseek.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessage {
    // Getter/Setter

    private String role;
    private String content;
//    private String reasoningContent;
    // Jackson 序列化需要默认构造方法



    // 预定义角色常量
    public static class Role {
        public static final String SYSTEM = "system";
        public static final String USER = "user";
        public static final String ASSISTANT = "assistant";
    }

    // 工厂方法
    public static ChatMessage createSystemMessage(String content) {
        return new ChatMessage(Role.SYSTEM, content);
    }

    public static ChatMessage createUserMessage(String content) {
        return new ChatMessage(Role.USER, content);
    }

}