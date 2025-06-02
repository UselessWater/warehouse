package com.uselesswater.warehouse.deepseek.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 标准响应结构
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;


    // 成功响应工厂方法
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }
}