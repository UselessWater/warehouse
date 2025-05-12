package com.uselesswater.warehouse.mapper;


import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.OutStoreDto;
import org.apache.ibatis.annotations.Param;

/**
* @author 30373
* @description 针对表【out_store(出库单)】的数据库操作Mapper
* @createDate 2025-05-10 23:04:05
* @Entity com.uselesswater.warehouse.beans.OutStore
*/
public interface OutStoreMapper  {

    /**
     *  插入出库信息
     * @param outStore 出库信息
     * @return 插入结果
     */
    Integer insertOutStore(@Param("outStore") OutStoreDto outStore);
}
