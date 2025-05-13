package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.InStore;
import com.uselesswater.warehouse.beans.dto.ConfirmInStoreDto;
import com.uselesswater.warehouse.beans.dto.InStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.vo.InStoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 30373
* @description 针对表【in_store(入库单)】的数据库操作Mapper
* @createDate 2025-05-13 09:31:53
* @Entity com.uselesswater.warehouse.beans.InStore
*/
public interface InStoreMapper {


    /**
     * 添加入库单
     * @param inStore 入库单对象
     * @return 返回结果
     */
    Integer insertInStore(InStore inStore);

    /**
     * 根据条件查询入库单总行数
     * @param inStoreDto 入库单查询条件
     * @return 返回行数
     */
    Integer queryInStoreRowCount(@Param("inStoreDto") InStoreDto inStoreDto);
    /**
     * 根据条件分页查询入库单
     * @param page 分页对象
     * @param inStoreDto 入库单查询条件
     * @return 返回入库单vo对象列表
     */
    List<InStoreVo> queryInStoreByPage(@Param("page") Page page, @Param("inStoreDto") InStoreDto inStoreDto);

    /**
     * 更新入库单状态
     * @param confirmInStoreDto 确认入库状态的dto对象
     * @return 返回受影响行数
     */
    Integer updateInStoreIsIn(@Param("confirmInStoreDto") ConfirmInStoreDto confirmInStoreDto);
}
