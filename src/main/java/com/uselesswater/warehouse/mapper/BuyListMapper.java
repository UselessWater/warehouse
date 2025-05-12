package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.BuyList;
import org.apache.ibatis.annotations.Param;

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
}
