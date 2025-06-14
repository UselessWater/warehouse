package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.Store;
import com.uselesswater.warehouse.beans.dto.InStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.beans.vo.InStoreVo;
import com.uselesswater.warehouse.mapper.InStoreMapper;
import com.uselesswater.warehouse.mapper.StoreMapper;
import com.uselesswater.warehouse.service.StoreService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30373
* @description 针对表【store(仓库表)】的数据库操作Service实现
* @createDate 2025-05-07 16:17:21
*/
@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreMapper storeMapper;

    @Override
    public Result getAllStore() {
        //查询所有仓库，返回集合
        List<Store> storeList = storeMapper.selectAllStore();
        return Result.ok(storeList);
    }
}
