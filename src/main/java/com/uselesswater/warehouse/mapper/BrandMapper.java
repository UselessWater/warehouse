package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Brand;

import java.util.List;

/**
* @author 30373
* @description 针对表【brand(品牌)】的数据库操作Mapper
* @createDate 2025-05-07 20:03:55
* @Entity com.uselesswater.warehouse.beans.Brand
*/
public interface BrandMapper  {

    /**
     * 查询所有品牌
     * @return 返回品牌列表
     */
    List<Brand> selectAllBrand();
}
