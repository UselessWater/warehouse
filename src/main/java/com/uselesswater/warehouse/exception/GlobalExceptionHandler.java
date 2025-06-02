package com.uselesswater.warehouse.exception;

import com.uselesswater.warehouse.beans.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 全局异常处理
 * @author uselesswater
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.error("业务异常", e);
        return Result.err(Result.CODE_ERR_BUSINESS, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统错误", e);
        return Result.err(Result.CODE_ERR_SYS, "服务器异常");
    }

}
