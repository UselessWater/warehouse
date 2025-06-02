package com.uselesswater.warehouse.deepseek.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;

    // 嵌套类
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private int index;
        private ChatMessage message;
        private String finishReason;

        // Getter/Setter
        public int getIndex() { return index; }
        public void setIndex(int index) { this.index = index; }
        public ChatMessage getMessage() { return message; }
        public void setMessage(ChatMessage message) { this.message = message; }
        public String getFinishReason() { return finishReason; }
        public void setFinishReason(String finishReason) { this.finishReason = finishReason; }
    }

    // Getter/Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getObject() { return object; }
    public void setObject(String object) { this.object = object; }
    public long getCreated() { return created; }
    public void setCreated(long created) { this.created = created; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public List<Choice> getChoices() { return choices; }
    public void setChoices(List<Choice> choices) { this.choices = choices; }
}