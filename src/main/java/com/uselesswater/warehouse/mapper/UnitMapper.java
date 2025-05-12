package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Unit;

import java.util.List;

/**
* @author 30373
* @description 针对表【unit(规格单位表)】的数据库操作Mapper
* @createDate 2025-05-07 20:04:23
* @Entity com.uselesswater.warehouse.beans.Unit
*/
public interface UnitMapper {

    /**
     * 查询所有单位
     * @return 返回单位列表
     */
    List<Unit> selectAllUnit();
}
