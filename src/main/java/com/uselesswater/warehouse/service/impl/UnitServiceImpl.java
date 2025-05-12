package com.uselesswater.warehouse.service.impl;


import com.uselesswater.warehouse.beans.Unit;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.UnitMapper;
import com.uselesswater.warehouse.service.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30373
* @description 针对表【unit(规格单位表)】的数据库操作Service实现
* @createDate 2025-05-07 20:04:23
*/
@Service
public class UnitServiceImpl implements UnitService {

    private final UnitMapper unitMapper;

    public UnitServiceImpl(UnitMapper unitMapper) {
        this.unitMapper = unitMapper;
    }

    @Override
    public Result getAllUnit() {

        //查询所有单位
        List<Unit> unitList = unitMapper.selectAllUnit();
        return Result.ok(unitList);
    }
}
