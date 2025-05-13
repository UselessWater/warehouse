package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.dto.InStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【store(仓库表)】的数据库操作Service
* @createDate 2025-05-07 16:17:21
*/
public interface StoreService{
    /**
     *  获取所有仓库
     * @return 返回自定义结果对象
     */
    Result getAllStore();


}
