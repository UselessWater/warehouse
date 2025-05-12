package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.dto.Result;

/**
* @author 30373
* @description 针对表【brand(品牌)】的数据库操作Service
* @createDate 2025-05-07 20:03:55
*/
public interface BrandService{
    /**
     *  获取所有品牌
     * @return 返回自定义结果对象
     */
    Result getAllBrand();

}
