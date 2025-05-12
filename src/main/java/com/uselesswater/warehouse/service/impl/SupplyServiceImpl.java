package com.uselesswater.warehouse.service.impl;


import com.uselesswater.warehouse.beans.Supply;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.SupplyMapper;
import com.uselesswater.warehouse.service.SupplyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30373
* @description 针对表【supply(供货商)】的数据库操作Service实现
* @createDate 2025-05-07 16:20:06
*/
@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyMapper supplyMapper;

    public SupplyServiceImpl(SupplyMapper supplyMapper) {
        this.supplyMapper = supplyMapper;
    }

    @Override
    public Result getAllSupply() {

        //查询所有供应商
        List<Supply> supplyList = supplyMapper.selectAllSupply();
        return Result.ok(supplyList);
    }
}
