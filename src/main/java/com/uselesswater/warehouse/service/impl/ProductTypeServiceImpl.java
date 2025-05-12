package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.ProductType;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.ProductTypeMapper;
import com.uselesswater.warehouse.service.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 30373
* @description 针对表【product_type(商品分类表)】的数据库操作Service实现
* @createDate 2025-05-07 20:16:33
*/
@Slf4j
@Service
@CacheConfig(cacheNames = "com.uselesswater.warehouse.service.impl.ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeMapper productTypeMapper;

    public ProductTypeServiceImpl(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }

    @Override
    @Cacheable(key = "'all:typeTree'")
    public List<ProductType> getAllProductTypeTree() {

        //查询所有分类列表对象
        List<ProductType> allProductTypeListTree = productTypeMapper.selectAllProductType();

        //将分类列表转换为分类树列表
        List<ProductType> productTypeTree = convertToTree(allProductTypeListTree, 0);

        //返回封装了分类树列表
        return productTypeTree;
    }

    @Override
    public Result checkProductTypeCode(String typeCode) {
        //根据类型代码（typeCode）查询商品分类对象，注意：产品类型代码唯一
        ProductType productType = productTypeMapper.selectProductTypeByCode(typeCode);
        //假如为null，则typeCode不存在，返回结果data为true，否则为false
        return Result.ok(productType == null);
    }

    @Override
    @CacheEvict(key = "'all:typeTree'")
    public  Result  addProductType(ProductType productType) {
        try {
            //校验typeName是否存在
            ProductType type = productTypeMapper.selectProductTypeByName(productType.getTypeName());
            if (type != null) {
               return Result.err(Result.CODE_ERR_BUSINESS,"商品类型名称已经存在！");
            }
            //这里有个并发问题，需要加锁，因为如果我们在操作的时候，这个商品编号已经被其他人添加，那么考虑出现问题(这里为了翻遍不做操作了)
            //添加商品类型
            Integer row = productTypeMapper.addProductType(productType);
            return row > 0 ? Result.ok("添加商品类型成功！") : Result.err(Result.CODE_ERR_BUSINESS, "添加商品类型失败!");
        } catch (Exception e) {
            log.info("系统错误，添加商品类型失败！{}",e.getMessage());
            return Result.err(Result.CODE_ERR_SYS,"系统错误，添加商品类型失败！");
        }
    }

    @Override
    @CacheEvict(key = "'all:typeTree'")
    public Result deleteProductType(Integer typeId) {
        try {
            //删除商品类型，需要考虑到她是否有字类型，所以我们在删除商品类型时，如果有子类型，可以同时删除parent_id = typeId
            Integer row  = productTypeMapper.deleteProductType(typeId);
            return row > 0 ? Result.ok("删除商品类型成功！") : Result.err(Result.CODE_ERR_BUSINESS, "删除商品类型失败!");
        } catch (Exception e) {
            log.info("系统错误，删除商品类型失败！{}",e.getMessage());
            return Result.err(Result.CODE_ERR_SYS,"系统错误，删除商品类型失败！");
        }
    }

    @Override
    @CacheEvict(key = "'all:typeTree'")
    public Result setProductType(ProductType productType) {
        try {
            //校验商品名称是否唯一
            ProductType dbProductType = productTypeMapper.selectProductTypeByName(productType.getTypeName());
            if (dbProductType != null && !productType.getTypeName().equals(dbProductType.getTypeName())) {
                return Result.err(Result.CODE_ERR_BUSINESS,"商品类型名称已经存在！");
            }
            //修改商品类型
            Integer row = productTypeMapper.updateProductType(productType);

            return row > 0 ? Result.ok("修改商品类型成功！") : Result.err(Result.CODE_ERR_BUSINESS, "修改商品类型失败!");
        } catch (Exception e) {
            log.info("系统错误，修改商品类型失败！{}",e.getMessage());
            return Result.err(Result.CODE_ERR_SYS,"系统错误，修改商品类型失败！");
        }
    }

    //将分类列表转换为分类树列表
    private List<ProductType> convertToTree(List<ProductType> allProductTypeListTree, Integer parentId) {

        //创建一个集合用于存放分类树列表
        List<ProductType> firstLevelTree = new ArrayList<>();

        //遍历所有分类列表,获取顶级 分类对象，存入集合
        allProductTypeListTree.forEach(productType -> {

            //提取出列表中的pid和我们一级pid相同的 分类对象，存入集合
            if (parentId.equals(productType.getParentId())) {
                firstLevelTree.add(productType);
            }
        });

        //遍历一级级分类列表,递归查询二级分类树
        firstLevelTree.forEach(productType -> {
            //获取二级分类列表
            List<ProductType> secondLevelTree = convertToTree(allProductTypeListTree, productType.getTypeId());

            //将二级分类列表存入一级分类对象中的children属性
            productType.setChildProductCategory(secondLevelTree);

        });

        return firstLevelTree;
    }
}
