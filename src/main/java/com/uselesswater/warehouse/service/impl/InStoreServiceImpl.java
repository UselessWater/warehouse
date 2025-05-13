package com.uselesswater.warehouse.service.impl;


import com.uselesswater.warehouse.beans.BuyList;
import com.uselesswater.warehouse.beans.InStore;
import com.uselesswater.warehouse.beans.dto.ConfirmInStoreDto;
import com.uselesswater.warehouse.beans.dto.InStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.beans.vo.InStoreVo;
import com.uselesswater.warehouse.mapper.BuyListMapper;
import com.uselesswater.warehouse.mapper.InStoreMapper;
import com.uselesswater.warehouse.mapper.ProductMapper;
import com.uselesswater.warehouse.service.InStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
* @author 30373
* @description 针对表【in_store(入库单)】的数据库操作Service实现
* @createDate 2025-05-13 09:31:53
*/
@Slf4j
@Service
public class InStoreServiceImpl implements InStoreService {

    private final InStoreMapper inStoreMapper;
    private final BuyListMapper buyListMapper;
    private final ProductMapper productMapper;

    public InStoreServiceImpl(InStoreMapper inStoreMapper, BuyListMapper buyListMapper, ProductMapper productMapper) {
        this.inStoreMapper = inStoreMapper;
        this.buyListMapper = buyListMapper;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public Result addInStore(BuyList buyList,Integer userId) {

        try {
            //创建入库单对象，用于封装入库单数据对象
            InStore inStore = new InStore();
            inStore.setStoreId(buyList.getStoreId());
            inStore.setProductId(buyList.getProductId());
            inStore.setInNum(buyList.getFactBuyNum());
            inStore.setCreateBy(userId);

            //1. 生成入库单，此时isIn字段为0，表示未入库
            Integer i = inStoreMapper.insertInStore(inStore);
            if (i > 0) {//成功添加入库单
                //2. 修改采购单的isIn字段为1，表示已经入库
                Integer j = buyListMapper.updateBuyListIsInByBuyId(buyList.getBuyId());
                if (j > 0) {
                    return Result.ok("生成入库单成功！");
                }
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.err(Result.CODE_ERR_BUSINESS,"生成入库单失败！");
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS,"生成入库单失败！");
        } catch (Exception e) {
            log.info("系统错误，生成入库单失败！{}",e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_SYS,"系统错误，生成入库单失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result confirmInStore(ConfirmInStoreDto confirmInStoreDto) {

        try {
            confirmInStoreDto.setIsIn("1");
            //更新入库表中入库状态
            Integer i = inStoreMapper.updateInStoreIsIn(confirmInStoreDto);
            if (i > 0) {
                //修改商品表中的库存数量
                Integer j = productMapper.addProductInventByProductId(confirmInStoreDto);
                if (j > 0) {
                    return Result.ok("确认入库成功！");
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Result.err(Result.CODE_ERR_BUSINESS, "确认入库失败！");
                }
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "确认入库失败！");

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("系统错误，确认入库失败！{}",e.getMessage());
            return Result.err(Result.CODE_ERR_SYS, "系统错误，确认入库失败");
        }
    }

    @Override
    public Result getInStoreListByPage(Page page, InStoreDto inStoreDto) {

        try {
            //分页动态查询所有行数
            Integer totalNum = inStoreMapper.queryInStoreRowCount(inStoreDto);

            //计算page对象中的属性值
            page.setTotalNum(totalNum);
            page.setPageCount(page.getPageCount());
            page.setLimitIndex(page.getLimitIndex());

            //动态分页查询所有入库列表
            List<InStoreVo> inStoreVoList = inStoreMapper.queryInStoreByPage(page,inStoreDto);
            page.setResultList(inStoreVoList);

            return Result.ok(page);
        } catch (Exception e) {
            log.info("系统错误！:\n{}\n",e.getMessage());
            return Result.err(Result.CODE_ERR_SYS,"系统错误，查询入库单失败！");
        }
    }

}
