package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.BuyListDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.BuyListService;
import com.uselesswater.warehouse.service.StoreService;
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

    public BuyListController(BuyListService buyListService, StoreService storeService) {
        this.buyListService = buyListService;
        this.storeService = storeService;
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
}
