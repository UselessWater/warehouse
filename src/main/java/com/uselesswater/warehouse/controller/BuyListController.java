package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.BuyListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public BuyListController(BuyListService buyListService) {
        this.buyListService = buyListService;
    }

    /*添加采购单*/
    @PostMapping("/purchase-add")
    public Result addBuyList(@RequestBody BuyList buyList){
        return buyListService.addBuyList(buyList);
    }
}
