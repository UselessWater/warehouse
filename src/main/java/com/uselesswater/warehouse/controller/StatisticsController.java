package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.ProductService;
import com.uselesswater.warehouse.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: StatisticsController  @date 2025/5/16 18:13  @author UselessWater  @jdk_version 17
 *
 * @description 统计查询相关控制器
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }


    //获取尚酷库存量，用于统计
    @GetMapping("/store-invent")
    public Result getStoreInvent(){
        return statisticsService.getStoreInvent();
    }
}
