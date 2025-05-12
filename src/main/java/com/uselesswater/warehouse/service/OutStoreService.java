package com.uselesswater.warehouse.service;


import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.OutStoreDto;
import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【out_store(出库单)】的数据库操作Service
* @createDate 2025-05-10 23:04:05
*/
public interface OutStoreService {
    /**
     * 添加出库单
     * @param outStore 出库单对象
     * @return 返回结果
     */
    Result addOutStore(OutStoreDto outStore);
}
