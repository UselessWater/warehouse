package com.uselesswater.warehouse.service;


import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.FindOutStoreDto;
import com.uselesswater.warehouse.beans.dto.InsertOutStoreDto;
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
    Result addOutStore(InsertOutStoreDto outStore);

    /**
     * 分页查询出库单列表业务方法
     * @param findOutStoreDto 封装了查询条件
     * @return 返回Result结果对象
     */
    Result findOutStoreListByPage(FindOutStoreDto findOutStoreDto);

    /**
     * 确认出库单业务方法
     * @param outStore 出库单对象,需要用到的outsId、productId、outNum、tallyId
     * @return 返回Result结果对象
     */
    Result outStoreConfirm(OutStore outStore);
}
