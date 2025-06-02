package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.FindOutStoreDto;
import com.uselesswater.warehouse.beans.dto.InsertOutStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.OutStoreService;
import com.uselesswater.warehouse.service.StoreService;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * className: OutStoreController  @date 2025/5/10 23:13  @author UselessWater  @jdk_version 17
 *
 * @description 出库
 */
@RestController
@RequestMapping("/outstore")
public class OutStoreController {

    @Resource
    private OutStoreService outStoreService;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private StoreService storeService;

    /*添加出库单*/
    @PostMapping("/outstore-add")
    public Result addOutStore(@RequestBody InsertOutStoreDto outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token) {
        outStore.setOutPrice();
        int userId = jwtUtils.getCurrentUser(token).getUserId();
        outStore.setCreateBy(userId);
        outStore.setTallyId(userId);
        return outStoreService.addOutStore(outStore);
    }


    /*分页查询出库列表*/
    @GetMapping("/outstore-page-list")
    public Result getOutStoreListByPage(FindOutStoreDto findOutStoreDto) {

        //查询出库列表
        return  outStoreService.findOutStoreListByPage(findOutStoreDto);
    }

    //查询所有的仓库列表
    @GetMapping("/store-list")
    public Result getStoreList(){
        return storeService.getAllStore();
    }

    //确认出库
    @PutMapping("/outstore-confirm")
    public Result confirmOutStore(@RequestBody OutStore outStore ,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        outStore.setTallyId(jwtUtils.getCurrentUser(token).getUserId());
        return outStoreService.outStoreConfirm(outStore);
    }


}
