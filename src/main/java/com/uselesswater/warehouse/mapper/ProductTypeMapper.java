package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 30373
* @description 针对表【product_type(商品分类表)】的数据库操作Mapper
* @createDate 2025-05-07 20:16:33
* @Entity com.uselesswater.warehouse.beans.ProductType
*/
public interface ProductTypeMapper {

    /**
     * 查询所有商品分类
     * @return 返回分类对象列表
     */
    List<ProductType> selectAllProductType();

    /**
     *  根据类型代码查询商品类型
     * @param typeCode 类型代码
     * @return 商品类型
     */
    ProductType selectProductTypeByCode(@Param("typeCode") String typeCode);

    /**
     *  根据商品类型名称查询商品类型
     * @param typeName 商品类型名称
     * @return 商品类型
     */
    ProductType selectProductTypeByName(@Param("typeName") String typeName);

    /**
     *  添加商品类型
     * @param productType 商品类型对象
     * @return 添加成功返回1，添加失败返回0，首影响行数
     */
    Integer addProductType(ProductType productType);

    /**
     *  删除商品分类：<br>
     *  根据{@code typeId}删除商品分类的同时，我们也要删除typeId下的子类型，所以，我们也会删除parent_id = #{typeId}的所有商品类型
     * @param typeId 商品类型ID
     * @return 删除结果
     */
    Integer deleteProductType(@Param("typeId") Integer typeId);

    /**
     * 修改商品分类，注意这里仅仅修改商品分类名称和商品描述
     * @param productType 商品分类对象
     * @return 修改结果
     */
    Integer updateProductType(@Param("productType") ProductType productType);
}
