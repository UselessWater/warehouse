package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.BuyListDto;
import com.uselesswater.warehouse.beans.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 30373
* @description 针对表【buy_list(采购单)】的数据库操作Mapper
* @createDate 2025-05-10 11:54:14
* @Entity com.uselesswater.warehouse.beans.BuyList
*/
public interface BuyListMapper {

    /**
     *  添加采购单
     * @param buyList 采购单对象
     * @return 受影响行数
     */
    Integer insertBuyList(@Param("buyList") BuyList buyList);

    /**
     * 根据条件查询采购单行数
     * @param buyListDto  查询条件
     * @return 行数
     */
    Integer selectBuyListRowCount(@Param("buyListDto") BuyListDto buyListDto);

    /**
     *  分页查询采购单
     * @param buyListDto  查询条件
     * @param page  分页对象
     * @return 采购单列表
     */
    List<BuyListDto> selectBuyListByPage(@Param("buyListDto") BuyListDto buyListDto, @Param("page") Page page);

    /**
     * 根据id删除采购单
     * @param buyId 采购单ID
     * @return 返回结果Result对象
     */
    Integer deleteBuyListById(@Param("buyId") Integer buyId);

    /**
     * 根据id修改采购单
     * @param buyList 采购单对象
     * @return 返回结果Result对象
     */
    Integer updateBuyList(@Param("buyList") BuyList buyList);

    /**
     * 根据id修改采购单入库状态
     * @param buyId 采购单ID
     * @return 返回结果
     */
    Integer updateBuyListIsInByBuyId(@Param("buyId") Integer buyId);
}
