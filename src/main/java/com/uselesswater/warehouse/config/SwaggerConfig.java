package com.uselesswater.warehouse.config;

import com.uselesswater.warehouse.utils.WarehouseConstants;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 配置安全方案（保持与你的token头一致）
@SecurityScheme(
    name = "WarehouseAuth",  // 安全方案名称
    type = SecuritySchemeType.APIKEY,
    in = SecuritySchemeIn.HEADER,
    paramName = WarehouseConstants.HEADER_TOKEN_NAME  // 你的token头名称
)
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 全局安全要求（所有接口默认需要认证）
                .addSecurityItem(new SecurityRequirement().addList("WarehouseAuth"));
    }
}