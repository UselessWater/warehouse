package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.ProductType;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.ProductService;
import com.uselesswater.warehouse.service.ProductTypeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className: ProductTypeController  @date 2025/5/10 12:42  @author UselessWater  @jdk_version 17
 *
 * @description 商品分类控制器
 */
@RestController
@RequestMapping("/productCategory")
public class ProductTypeController {

    @Resource
    private ProductTypeService productTypeService;

    /*获取所有商品分类树*/
    @GetMapping("/product-category-tree")
    public Result getProductTypeTree() {
        List<ProductType> productTypeTree = productTypeService.getAllProductTypeTree();
        return Result.ok(productTypeTree);
    }

    /*校验分类编码是否已经存在*/
    @GetMapping("/verify-type-code")
    public Result checkProductTypeCode(@RequestParam("typeCode") String typeCode) {
        return productTypeService.checkProductTypeCode(typeCode);
    }

    /*添加商品分类*/
    @PostMapping("/type-add")
    public Result addProductType(@RequestBody ProductType productType) {
        //添加商品类型
       return productTypeService.addProductType(productType);
    }

    /*删除商品分类*/
    @DeleteMapping("/type-delete/{typeId}")
    public Result deleteProductType(@PathVariable("typeId") Integer typeId) {
        return productTypeService.deleteProductType(typeId);
    }

    /*修改商品分类，仅仅修改商品分类名称，和商品分类描述*/
    @PutMapping("/type-update")
    public Result updateProductType(@RequestBody ProductType productType) {
        return productTypeService.setProductType(productType);
    }



}
