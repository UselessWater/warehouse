package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.BuyListDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.BuyListService;
import com.uselesswater.warehouse.service.InStoreService;
import com.uselesswater.warehouse.service.StoreService;
import com.uselesswater.warehouse.utils.CurrentUser;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import org.springframework.web.bind.annotation.*;

/**
 * className: BuyListController  @date 2025/5/10 12:06  @author UselessWater  @jdk_version 17
 *
 * @description 采购控制器
 */
@RestController
@RequestMapping("/purchase")
public class BuyListController {

    //通过构造方法注入
    private final BuyListService buyListService;
    private final StoreService storeService;
    private final InStoreService inStoreService;
    private final JwtUtils jwtUtils;

    public BuyListController(BuyListService buyListService, StoreService storeService, InStoreService inStoreService, JwtUtils jwtUtils) {
        this.buyListService = buyListService;
        this.storeService = storeService;
        this.inStoreService = inStoreService;
        this.jwtUtils = jwtUtils;
    }

    /*添加采购单*/
    @PostMapping("/purchase-add")
    public Result addBuyList(@RequestBody BuyList buyList){
        return buyListService.addBuyList(buyList);
    }

    /*分页查询采购单列表*/
    @GetMapping("/purchase-page-list")
    public Result getBuyList( BuyListDto buyListDto,Page page){
        return buyListService.getBuyListByPage(buyListDto,page);
    }

    /*查询所有的仓库列表*/
    @GetMapping("/store-list")
    public Result getStoreList(){
        return storeService.getAllStore();
    }

    /**
     * 删除指定ID的采购单。
     *
     * @param buyId 采购单的唯一标识符
     * @return Result 操作结果，包含成功状态或错误信息
     */
    @DeleteMapping("/purchase-delete/{buyId}")
    public Result deleteBuyList(@PathVariable("buyId") Integer buyId){
        return buyListService.removeBuyListById(buyId);
    }

    /*修改采购单*/
    @PutMapping("/purchase-update")
    public Result updateBuyList(@RequestBody BuyList buyList){
        return buyListService.updateBuyList(buyList);
    }

    /*生成入库单*/
    @PostMapping("/in-warehouse-record-add")
    public Result generateInStoreList(@RequestBody BuyList buyList,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = jwtUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        return inStoreService.addInStore(buyList,userId);
    }
}
