package com.uselesswater.warehouse.mapper;


import com.uselesswater.warehouse.beans.Supply;

import java.util.List;

/**
* @author 30373
* @description 针对表【supply(供货商)】的数据库操作Mapper
* @createDate 2025-05-07 16:20:06
* @Entity com.uselesswater.warehouse.beans.Supply
*/
public interface SupplyMapper {

    /**
     *  查询所有供应商
     * @return 供应商列表
     */
    List<Supply> selectAllSupply();
}
