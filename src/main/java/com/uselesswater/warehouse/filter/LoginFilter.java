package com.uselesswater.warehouse.filter;

import com.alibaba.fastjson.JSON;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {

    @Setter
    private StringRedisTemplate stringRedisTemplate;

    // 保持原有变量名不变
    private final List<String> whiteList = new ArrayList<>();

    //用于比较url的ant风格的值是否相同的对象
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) {
        // 初始化原有白名单
        whiteList.add("/captcha/captchaImage");
        whiteList.add("/login");
        whiteList.add("/logout");
        whiteList.add("/swagger-ui.html");
        whiteList.add("/product/img-upload");//放行图片上传路径

        // 读取配置中的排除路径
        String exclusions = filterConfig.getInitParameter("exclusions");
        if (exclusions != null && !exclusions.isEmpty()) {
            whiteList.addAll(Arrays.asList(exclusions.split(",")));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求的url，注意这个url是上下文后面的一串url，而不是带有上下文的uri。
        String servletPath = request.getServletPath();

        // 使用AntPathMatcher检查白名单
        boolean needFilter = true;
        for (String pattern : whiteList) {
            if (pathMatcher.match(pattern.trim(), servletPath)) {
                needFilter = false;
                break;
            }
        }

        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Token校验逻辑
        String clientToken = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        if (!StringUtils.hasText(clientToken)) {
            writeUnauthorizedResponse(response, "token缺失，请登录");
            return;
        }

        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(clientToken))) {
            writeUnauthorizedResponse(response, "token无效，请重新登录");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void writeUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, message);
        String json = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
            writer.flush();
        }
    }
}