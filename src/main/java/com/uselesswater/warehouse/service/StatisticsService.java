package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.dto.Result;

/**
 * className: StatisticsService  @date 2025/5/16 18:18  @author UselessWater  @jdk_version 17
 *
 * @description 统计相关业务
 */

public interface StatisticsService {

    /**
     *  获取每个仓库商品库存
     * @return 商品库存
     */
    Result getStoreInvent();


}
