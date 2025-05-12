package com.uselesswater.warehouse.config;

import com.uselesswater.warehouse.filter.LoginFilter;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * className: LoginFilterConfig  @date 2025/5/2 9:39  @author UselessWater  @jdk_version 17
 *
 * @description 登录过滤器配置
 */
@Configuration
public class LoginFilterConfig {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public FilterRegistrationBean<LoginFilter> filterFilterRegistrationBean() {
        LoginFilter loginFilter = new LoginFilter();
        //将redis模版注入
        loginFilter.setStringRedisTemplate(stringRedisTemplate);
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        //注册过滤器
        filterRegistrationBean.setFilter(loginFilter);

        //设置过滤器过滤路径
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));

        // 排除Swagger相关路径
        filterRegistrationBean.addInitParameter("exclusions",
                "/swagger-ui/**,/v3/api-docs/**,/webjars/**,/swagger-resources/**,/img/upload/**");

        return filterRegistrationBean;
    }
}
