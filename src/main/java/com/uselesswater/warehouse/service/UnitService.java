package com.uselesswater.warehouse.service;


import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【unit(规格单位表)】的数据库操作Service
* @createDate 2025-05-07 20:04:23
*/
public interface UnitService {
    /**
     * 查询所有单位列表
     * @return 返回自定义结果对象
     */
    Result getAllUnit();

}
