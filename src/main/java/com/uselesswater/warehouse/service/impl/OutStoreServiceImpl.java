package com.uselesswater.warehouse.service.impl;


import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.OutStoreDto;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.OutStoreMapper;
import com.uselesswater.warehouse.service.OutStoreService;
import org.springframework.stereotype.Service;

/**
* @author 30373
* @description 针对表【out_store(出库单)】的数据库操作Service实现
* @createDate 2025-05-10 23:04:05
*/
@Service
public class OutStoreServiceImpl implements OutStoreService {

    private final OutStoreMapper outStoreMapper;

    public OutStoreServiceImpl(OutStoreMapper outStoreMapper) {
        this.outStoreMapper = outStoreMapper;
    }

    @Override
    public Result addOutStore(OutStoreDto outStore) {

        //添加出库单
        Integer row = outStoreMapper.insertOutStore(outStore);
        return row > 0 ? Result.ok("添加出库单成功！") : Result.err(Result.CODE_ERR_BUSINESS,"添加出库单失败");
    }
}
