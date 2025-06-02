package com.uselesswater.warehouse.deepseek.beans;

// 用户请求封装类
public class UserRequest {
    private String question;  // 用户问题
    private String language;  // 可选参数：语言偏好
    private boolean needDetail; // 可选参数：是否需要详细回答

    // 无参构造方法（必须）
    public UserRequest() {}

    // 全参构造方法（可选）
    public UserRequest(String question, String language, boolean needDetail) {
        this.question = question;
        this.language = language;
        this.needDetail = needDetail;
    }

    // Getter 和 Setter 方法
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public boolean isNeedDetail() { return needDetail; }
    public void setNeedDetail(boolean needDetail) { this.needDetail = needDetail; }
}