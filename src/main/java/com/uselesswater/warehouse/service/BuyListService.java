package com.uselesswater.warehouse.service;


import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.BuyListDto;
import com.uselesswater.warehouse.beans.dto.Page;
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
    /**
     * 根据条件分页查询采购单
     * @param buyListDto 采购单对象
     * @param page 分页对象
     * @return 返回结果
     */
    Result getBuyListByPage(BuyListDto buyListDto, Page page);

    /**
     * 根据id删除采购单
     * @param buyId 采购单id
     * @return 返回结果
     */
    Result removeBuyListById(Integer buyId);

    /**
     * 修改采购单
     * @param buyList 采购单对象，包含：采购数量、实际采购数量、采购单ID
     * @return 返回结果
     */
    Result updateBuyList(BuyList buyList);
}
