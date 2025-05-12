package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.ProductType;
import com.uselesswater.warehouse.beans.dto.Result;


import java.util.List;

/**
* @author 30373
* @description 针对表【product_type(商品分类表)】的数据库操作Service
* @createDate 2025-05-07 20:16:33
*/
public interface ProductTypeService {
    /**
     *  获取所有商品类型树
     * @return 商品类型树列表
     */
    List<ProductType> getAllProductTypeTree();

    /**
     *  检查商品类型代码
     * @param typeCode 商品类型代码
     * @return 检查结果
     */
    Result checkProductTypeCode(String typeCode);

    /**
     *  添加商品类型
     * @param productType 商品类型对象
     * @return 返回结果对象
     */
    Result addProductType(ProductType productType);

    /**
     *  删除商品分类
     * @param typeId 商品类型ID
     * @return 删除结果
     */
    Result deleteProductType(Integer typeId);

    /**
     *  修改商品分类
     * @param productType 商品类型
     * @return Result 结果
     */
    Result setProductType(ProductType productType);
}
