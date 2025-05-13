package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.dto.BuyListDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.BuyListMapper;
import com.uselesswater.warehouse.service.BuyListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
            Integer row = buyListMapper.insertBuyList(buyList);
            return row > 0 ? Result.ok("添加采购单成功！") : Result.ok("添加采购单失败！");
        } catch (Exception e) {
            log.info(e.getMessage());
            return Result.ok("系统繁忙，添加采购单失败！");
        }

    }

    @Override
    public Result getBuyListByPage(BuyListDto buyListDto, Page page) {
        try {
            //查询总行数totalNum（分页查询）
            Integer totalNum = buyListMapper.selectBuyListRowCount(buyListDto);
            page.setTotalNum(totalNum);
            page.setPageCount(page.getPageCount());
            page.setLimitIndex(page.getLimitIndex());
            //分页查询采购单
            List<BuyListDto> buyListDtoList =buyListMapper.selectBuyListByPage(buyListDto,page);
            if (buyListDtoList != null) {
                page.setResultList(buyListDtoList);
            }
            return Result.ok(page);
        } catch (Exception e) {
            getSysLogInfo(e);
            return Result.err(Result.CODE_ERR_SYS,"系统错误，查询采购单失败！");
        }
    }

    private static void getSysLogInfo(Exception e) {
        log.info("系统错误！{}", e.getMessage());
    }

    @Override
    public Result removeBuyListById(Integer buyId) {

        try {
            Integer row = buyListMapper.deleteBuyListById(buyId);
            return row > 0 ? Result.ok("删除采购单成功！") : Result.ok("删除采购单失败！");
        } catch (Exception e) {
            return Result.ok("系统繁忙，删除采购单失败！");
        }
    }

    @Override
    public Result updateBuyList(BuyList buyList) {

        try {
            Integer row = buyListMapper.updateBuyList(buyList);
            return row > 0 ? Result.ok("修改采购单成功！") : Result.ok("修改采购单失败！");
        } catch (Exception e) {
            getSysLogInfo(e);
            return Result.ok("系统繁忙，修改采购单失败！");
        }

    }
}
