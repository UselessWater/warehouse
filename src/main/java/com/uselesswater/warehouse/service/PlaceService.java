package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【place(产地)】的数据库操作Service
* @createDate 2025-05-07 20:04:46
*/
public interface PlaceService {
    /**
     * 查询所有产地
     * @return 返回封装 产地 列表的自定义Result对象
     */
    Result getAllPlace();
}
