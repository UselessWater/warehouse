package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.Product;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.ProductDto;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.ProductMapper;
import com.uselesswater.warehouse.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author 30373
 * @description 针对表【product(商品表)】的数据库操作Service实现
 * @createDate 2025-05-07 16:13:17
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Result getProductListByPage(Page page, ProductDto productDto) {

        //动态条件查询总行数：totalNum
        Integer totalNum = productMapper.selectProductRowCount(productDto);

        //计算page对象个属性的值
        page.setTotalNum(totalNum);
        page.setPageCount(page.getPageCount());
        page.setLimitIndex(page.getLimitIndex());

        //分页查询商品列表
        List<ProductDto> productDtoList = productMapper.selectProductListByPage(page, productDto);
        page.setResultList(productDtoList);

        return Result.ok(page);
    }

    @Value("${file.access-path}")//文件访问路径属性绑定
    private String imgAccessPath;

    @Override
    public Result addProduct(Product product) {

        //将图片路径拼接
        String imgs = product.getImgs();
        product.setImgs(imgAccessPath + imgs);

        Integer row = productMapper.insertProduct(product);
        if (row > 0) {
            return Result.ok("添加商品成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "添加商品失败！");
    }

    @Override
    public Result setProductState(Product product) {
        Integer row = productMapper.updateProductState(product);
        if (row > 0) {
            return Result.ok("设置商品状态成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "设置商品状态失败！");
    }

    @Override
    public Result deleteProductList(List<Integer> productIdList) {

        Integer row = productMapper.deleteProductByProductIds(productIdList);
        if (row > 0) {
            return Result.ok("删除商品成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "删除商品失败！");
    }

    @Override
    public Result updateProduct(Product product) {

        //处理图片路径问题
        String imgs = product.getImgs();

        //假如接收到的图片路径不包含imgAccessPath，则拼接图片路径
        if (!imgs.contains(imgAccessPath)) {
            //拼接图片路径
            product.setImgs(imgAccessPath + imgs);
        }

        List<Product> productList = null;//根据商品型号查询商品信息

        try {
            //处理productNum，判断商品型号是否唯一，不唯一(不是正修改的productId)，则添加失败
            productList = productMapper.selectProductByProductNum(product.getProductNum());
        } catch (Exception e) {
            log.info(e.getMessage());
            return Result.err(Result.CODE_ERR_BUSINESS, "系统繁忙请稍后再试!");
        }

        //定义标记表示productNum是否存在
        boolean flag = false;

        if (productList != null && !productList.isEmpty()) {

            if (productList.size() > 1) {
                return Result.err(Result.CODE_ERR_BUSINESS, "修改失败，商品型号已存在！");
            }

            //获取商品对象
            Product productInfo = productList.get(0);
            //根据productId验证根据productNum查询到的商品信息是否是正在修改的商品,如果是，则flag为true
            if (product.getProductId().equals(productInfo.getProductId())) {
                flag = true;
            }
        }

        //防止空指针
        if (productList == null) {
            productList = Collections.emptyList();
        }

        //如果flag为true，则表示根据productNum查询到的商品信息正在修改的商品，则可以修改
        if (flag || productList.isEmpty()) {
            //可以添加
            Integer row = productMapper.updateProductByProductId(product);
            return row > 0 ? Result.ok("修改商品成功！") : Result.err(Result.CODE_ERR_BUSINESS, "修改商品失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品型号已存在！");
    }
}




