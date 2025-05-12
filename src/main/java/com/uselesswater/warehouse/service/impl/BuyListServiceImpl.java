package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.BuyListMapper;
import com.uselesswater.warehouse.service.BuyListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author 30373
* @description 针对表【buy_list(采购单)】的数据库操作Service实现
* @createDate 2025-05-10 11:54:14
*/
@Slf4j
@Service
public class BuyListServiceImpl implements BuyListService {

    private final BuyListMapper buyListMapper;

    public BuyListServiceImpl(BuyListMapper buyListMapper) {
        this.buyListMapper = buyListMapper;
    }

    @Override
    public Result addBuyList(BuyList buyList) {
        try {
            buyList.setFactBuyNum(buyList.getBuyNum());
            Integer row = buyListMapper.insertBuyList(buyList);
            return row > 0 ? Result.ok("添加采购单成功！") : Result.ok("添加采购单失败！");
        } catch (Exception e) {
            log.info(e.getMessage());
            return Result.ok("系统繁忙，添加采购单失败！");
        }

    }
}
