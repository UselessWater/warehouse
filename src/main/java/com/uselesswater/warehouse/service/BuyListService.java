package com.uselesswater.warehouse.service;


import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【buy_list(采购单)】的数据库操作Service
* @createDate 2025-05-10 11:54:14
*/
public interface BuyListService {
    /**
     * 添加采购单
     * @param buyList 采购单对象
     * @return 返回结果
     */
    Result addBuyList(BuyList buyList);
}
