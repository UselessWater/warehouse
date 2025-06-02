package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.vo.StoreInventVo;

import java.util.List;

/**
 * className: StatisticsMapper  @date 2025/5/16 18:22  @author UselessWater  @jdk_version 17
 *
 * @description 统计查询相关mapper
 */

public interface StatisticsMapper {

    /**
     * 查询每个仓库商品库存
     * @return StoreInventVo仓库库存对象
     */
    List<StoreInventVo> selectStoreInventGroupByStoreIdAndStoreName();
}
