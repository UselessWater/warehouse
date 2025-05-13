package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.ConfirmInStoreDto;
import com.uselesswater.warehouse.beans.dto.InStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【in_store(入库单)】的数据库操作Service
* @createDate 2025-05-13 09:31:53
*/
public interface InStoreService {
    /**
     * 添加入库单
     * @param buyList 采购单对象，包含了入库单信息
     * @param userId 创建者
     * @return 返回结果对象
     */
    Result addInStore(BuyList buyList ,Integer userId);

    /**
     * 确认入库
     * @param confirmInStoreDto 确认入库对象，包含了入库单id和入库状态
     * @return 确认结果对象
     */
    Result confirmInStore(ConfirmInStoreDto confirmInStoreDto);

    /**
     * 分页获取入库记录
     * @param page 分页对象
     * @param inStoreDto 查询入库记录的查询条件
     * @return 返回结果
     */
    Result getInStoreListByPage(Page page, InStoreDto inStoreDto);
}
