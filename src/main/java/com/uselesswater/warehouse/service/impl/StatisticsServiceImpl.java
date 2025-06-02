package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.beans.vo.StoreInventVo;
import com.uselesswater.warehouse.mapper.StatisticsMapper;
import com.uselesswater.warehouse.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * className: StatisticsServiceImpl  @date 2025/5/16 18:19  @author UselessWater  @jdk_version 17
 *
 * @description 统计相关业务实现
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsMapper statisticsMapper;

    public StatisticsServiceImpl(StatisticsMapper statisticsMapper) {
        this.statisticsMapper = statisticsMapper;
    }

    @Override
    public Result getStoreInvent() {
        List<StoreInventVo> storeInventVoList = statisticsMapper.selectStoreInventGroupByStoreIdAndStoreName();
        return Result.ok(storeInventVoList);
    }
}
