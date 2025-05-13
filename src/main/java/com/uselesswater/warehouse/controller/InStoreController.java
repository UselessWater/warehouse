package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.dto.ConfirmInStoreDto;
import com.uselesswater.warehouse.beans.dto.InStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.InStoreService;
import com.uselesswater.warehouse.service.StoreService;
import org.springframework.web.bind.annotation.*;

/**
 * className: InStoreController  @date 2025/5/13 11:12  @author UselessWater  @jdk_version 17
 *
 * @description 入库管理控制器
 */
@RestController
@RequestMapping("/instore")
public class InStoreController {

    private final StoreService storeService;
    private final InStoreService inStoreService;

    public InStoreController(StoreService storeService, InStoreService inStoreService) {
        this.storeService = storeService;
        this.inStoreService = inStoreService;
    }

    //查询所有仓库列表
    @GetMapping("/store-list")
    public Result getStoreList(){
        return storeService.getAllStore();
    }

    //分页查询所有入库记录
    @GetMapping("/instore-page-list")
    public Result getInStoreList(Page page, InStoreDto inStoreDto){
        return inStoreService.getInStoreListByPage(page,inStoreDto);
    }

    //确认入库
    @PutMapping("/instore-confirm")
    public Result confirmInStore(@RequestBody ConfirmInStoreDto confirmInStoreDto){
        //确认入库
        return inStoreService.confirmInStore(confirmInStoreDto);
    }

}
