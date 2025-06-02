package com.uselesswater.warehouse.mapper;


import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.FindOutStoreDto;
import com.uselesswater.warehouse.beans.dto.InsertOutStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.vo.OutStoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    Integer insertOutStore(@Param("outStore") InsertOutStoreDto outStore);

    /**
     * 分局条件查询出库单总条数totalNum
     * @param findOutStoreDto 封装了查询条件
     * @return 返回totalNum总条数
     */
    Integer selectTotalNumByPage(@Param("findOutStoreDto") FindOutStoreDto findOutStoreDto);

    /**
     * 分页条件查询出库单
     * @param page 分页信息
     * @param findOutStoreDto 封装了查询条件
     * @return 返回分页后的出库单
     */
    List<OutStoreVo> selectOutStoreListByPage(@Param("page") Page page, @Param("findOutStoreDto") FindOutStoreDto findOutStoreDto);

    /**
     * 修改出库单状态和操作人ID
     * @param outStore 出库单对象
     * @return 修改结果
     */
    Integer setIsOutIsOutStore(OutStore outStore);

    /**
     * 减商品库存
     * @param outStore 出库单对象
     * @return 减库存结果
     */
    Integer deProductInvent(OutStore outStore);
}
