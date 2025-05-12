package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Store;

import java.util.List;

/**
* @author 30373
* @description 针对表【store(仓库表)】的数据库操作Mapper
* @createDate 2025-05-07 16:17:21
* @Entity com.uselesswater.warehouse.beans.Store
*/
public interface StoreMapper {

    /**
     *  查询所有仓库
     * @return 仓库列表
     */
    List<Store> selectAllStore();

}
