package com.uselesswater.warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

/*
    git颜色它们分别表示的含义：
        绿色，已经加入控制暂未提交
        红色，未加入版本控制
        蓝色，加入，已提交，有改动
        白色，加入，已提交，无改动
        灰色：版本控制已忽略文件。
*/
@SpringBootApplication
@EnableCaching//开启redis缓存版注解
@MapperScan(basePackages = "com.uselesswater.warehouse.mapper")
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
