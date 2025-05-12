package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.OutStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.OutStoreService;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    /*添加出库单*/
    @PostMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStoreDto outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token) {
        outStore.setOutPrice();
        int userId = jwtUtils.getCurrentUser(token).getUserId();
        outStore.setCreateBy(userId);
        outStore.setTallyId(userId);
        return outStoreService.addOutStore(outStore);
    }

    /*查询所有出库列表*/
    @GetMapping("/store-list")
    public Result getOutStoreList() {
        return null;
    }

    /*分页查询出库列表*/
    @GetMapping("/outstore-page-list")
    public Result getOutStoreListByPage() {
        return null;
    }
}
