package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Product;
import com.uselesswater.warehouse.beans.dto.ConfirmInStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.ProductDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 30373
* @description 针对表【product(商品表)】的数据库操作Mapper
* @createDate 2025-05-07 16:13:17
* @Entity com.uselesswater.warehouse.beans.Product
*/
public interface ProductMapper{
    /**
     *  根据产品信息查询产品行数
     * @param productDto 产品信息
     * @return 产品数量
     */
    Integer selectProductRowCount(@Param("productDto") ProductDto productDto);

    /**
     *  根据分页信息和产品信息查询产品列表
     * @param page 分页信息
     * @param productDto 产品信息
     * @return 产品列表
     */
    List<ProductDto> selectProductListByPage(@Param("page") Page page, @Param("productDto") ProductDto productDto);

    /**
     *  插入商品
     * @param product 商品对象
     * @return 插入结果
     */
    Integer insertProduct(@Param("product") Product product);

    /**
     * 更新商品状态
     * @param product 商品对象
     * @return 更新结果
     */
    Integer updateProductState(@Param("product") Product product);

    /**
     *  根据产品ID列表删除产品
     * @param productIdList 产品ID列表
     * @return 删除的产品数量
     */
    Integer deleteProductByProductIds(@Param("productIds") List<Integer> productIdList);

    /**
     *  根据商品编号查询商品
     * @param productNum 商品编号
     * @return 商品列表
     */
    List<Product> selectProductByProductNum(@Param("productNum") String productNum);

    /**
     *  根据商品ID更新商品信息
     * @param product 商品对象
     * @return 更新后的商品ID
     */
    Integer updateProductByProductId(@Param("product") Product product);

    /**
     *  根据商品ID增加商品库存
     * @param confirmInStoreDto 包含商品ID和入库数量
     * @return 增加后的商品ID
     */
    Integer addProductInventByProductId(@Param("confirmInStoreDto")ConfirmInStoreDto confirmInStoreDto);

    /**
     *  根据商品ID查询商品库存
     * @param productId 商品ID
     * @return 商品库存
     */
    Integer selectProductInventByProductId(Integer productId);
}




