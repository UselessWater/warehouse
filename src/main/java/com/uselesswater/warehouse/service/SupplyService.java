package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【supply(供货商)】的数据库操作Service
* @createDate 2025-05-07 16:20:06
*/
public interface SupplyService {
    /**
     *  获取所有供应商
     * @return 返回封装了Supply列表对象的Result对象
     */
    Result getAllSupply();
}
