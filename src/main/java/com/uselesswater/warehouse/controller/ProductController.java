package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.Product;
import com.uselesswater.warehouse.beans.ProductType;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.ProductDto;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.*;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * className: ProductController  @date 2025/5/7 20:25  @author UselessWater  @jdk_version 17
 *
 * @description 商品控制器
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private StoreService storeService;

    @Resource
    private BrandService brandService;

    @Resource
    private ProductTypeService productTypeService;

    @Resource
    private UnitService unitService;

    @Resource
    private PlaceService placeService;

    @Resource
    private SupplyService supplyService;
    @Resource
    private JwtUtils jwtUtils;


    /*根据条件分页查询商品列表 /product/product-page-list 请求方法:GET*/
    @GetMapping("/product-page-list")
    public Result getProductPageList(Page page, ProductDto productDto) {
        return productService.getProductListByPage(page, productDto);
    }


    /*获取所有仓库 /product/store-list 请求方法: GET*/
    @GetMapping("/store-list")
    public Result getStoreList() {
        return storeService.getAllStore();
    }


    /*获取所有品牌 /product/brand-list 请求方法: GET*/
    @GetMapping("/brand-list")
    public Result getBrandList() {
        return brandService.getAllBrand();
    }

    /*获取所有分类 /product/category-tree 请求方法: GET*/
    @GetMapping("/category-tree")//"/product-category-tree"
    public Result getCategoryTree() {
        List<ProductType> productTypeTree = productTypeService.getAllProductTypeTree();
        return Result.ok(productTypeTree);
    }

    /*查询单位列表 /product/unit-list 请求方法:GET*/
    @GetMapping("/unit-list")
    public Result getUnitList() {
        return unitService.getAllUnit();
    }

    /*查询产地列表 /product/place-list 请求方法:GET*/
    @GetMapping("/place-list")
    public Result getPlaceList() {
        return placeService.getAllPlace();
    }

    /*查询供货商列表 /product/supply-list 请求方法:GET*/
    @GetMapping("/supply-list")
    public Result getSupplyList() {
        return supplyService.getAllSupply();
    }

    @Value("${file.upload-path}")//文件上传类路径属性绑定
    private String uploadPath;

    /*山川商品图片*/
    @CrossOrigin//解决跨域问题，允许跨域
    @PostMapping("/img-upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            /*
            * MultipartFile接口对象用于文件上传,SpringBoot3中，我们直接接收参数时，
            * 需要显示指定请求参数，参数名需要显示指定。注解，才能解析到MultiPartFile对象，
            * 只要是请求参数，都要显示指定，否则解析不要。
            * 如果不指定，编译时，需要加上-parameters参数才能编译成功运行。
            * springBoot2不会出现这个问题。
            * */
            //文件上传


            //解析类路径为磁盘路径（绝对路路径）的 File对象
            File absolutePath = ResourceUtils.getFile(uploadPath);

            //获取文件名，根据multiPartFile对象获取上传的文件名
            String fileName = file.getOriginalFilename();//注意需要判空

            //拼接文件路径，封装为file对象
            assert fileName != null;
            File reallyPathFile = new File(absolutePath, fileName);

            //将文件上传到file对象中
            file.transferTo(reallyPathFile);//此处传的参数是上传文件磁盘路径封装的File对象

            return Result.ok("图片上传成功！");

        } catch (IOException e) {
            return Result.err(Result.CODE_ERR_BUSINESS,  "图片上传失败！因为："+e.getMessage());
//            return Result.err(Result.CODE_ERR_BUSINESS, "图片上传失败！");
        }
    }


    /*添加商品接口*/
    @PostMapping("/product-add")
    public Result addProduct(@RequestBody Product product,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //解析token，将userId值赋值给product对象
        product.setCreateBy(jwtUtils.getCurrentUser(token).getUserId());
        return productService.addProduct(product);
    }

    /*商品上下架功能*/
    @PutMapping("/state-change")
    public Result changeProductState(@RequestBody Product product) {
        return productService.setProductState(product);
    }

    /*批量删除商品*/
    @DeleteMapping("/product-list-delete")
    public Result deleteProductList(@RequestBody List<Integer> productIdList) {
        return productService.deleteProductList(productIdList);
    }

    /*删除单条商品记录*/
    @DeleteMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable("productId") Integer productId) {
        //将商品单个商品ID存入数组中，转为list集合
        List<Integer> idList = Collections.singletonList(productId);
        return productService.deleteProductList(idList);
    }

    /*修改商品信息*/
    @PutMapping("/product-update")
    public Result updateProduct(@RequestBody Product product,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //解析token，将userId值赋值给product对象的updateBy属性
        product.setUpdateBy(jwtUtils.getCurrentUser(token).getUserId());
        return productService.updateProduct(product);
    }





}
