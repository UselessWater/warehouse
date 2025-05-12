package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.Product;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.ProductDto;
import com.uselesswater.warehouse.beans.dto.Result;

import java.util.List;

/**
* @author 30373
* @description 针对表【product(商品表)】的数据库操作Service
* @createDate 2025-05-07 16:13:17
*/
public interface ProductService{
    /**
     *  根据分页信息和产品信息获取产品列表
     * @param page 分页信息
     * @param productDto 产品信息
     * @return 产品列表
     */
    Result getProductListByPage(Page page, ProductDto productDto);

    /**
     *  添加商品
     * @param product 商品对象
     * @return Result 返回结果
     */
    Result addProduct(Product product);

    /**
     *  设置商品状态
     * @param product 商品对象
     * @return 结果
     */
    Result setProductState(Product product);

    /**
     * 根据productId列表批量删除商品
     * @param productIdList 商品id列表
     * @return 删除结果
     */
    Result deleteProductList(List<Integer> productIdList);

    /**
     *  更新商品信息
     * @param product 商品对象
     * @return 返回更新结果
     */
    Result updateProduct(Product product);
}
