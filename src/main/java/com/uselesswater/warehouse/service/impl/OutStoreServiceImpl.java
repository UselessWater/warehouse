package com.uselesswater.warehouse.service.impl;


import com.uselesswater.warehouse.beans.OutStore;
import com.uselesswater.warehouse.beans.dto.FindOutStoreDto;
import com.uselesswater.warehouse.beans.dto.InsertOutStoreDto;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.beans.vo.OutStoreVo;
import com.uselesswater.warehouse.exception.BusinessException;
import com.uselesswater.warehouse.mapper.OutStoreMapper;
import com.uselesswater.warehouse.mapper.ProductMapper;
import com.uselesswater.warehouse.service.OutStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author 30373
 * @description 针对表【out_store(出库单)】的数据库操作Service实现
 * @createDate 2025-05-10 23:04:05
 */
@Slf4j
@Service
public class OutStoreServiceImpl implements OutStoreService {

    private final OutStoreMapper outStoreMapper;
    private final ProductMapper productMapper;

    public OutStoreServiceImpl(OutStoreMapper outStoreMapper, ProductMapper productMapper) {
        this.outStoreMapper = outStoreMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Result addOutStore(InsertOutStoreDto outStore) {

        //添加出库单
        Integer row = outStoreMapper.insertOutStore(outStore);
        return row > 0 ? Result.ok("添加出库单成功！") : Result.err(Result.CODE_ERR_BUSINESS, "添加出库单失败");
    }

    @Override
    public Result findOutStoreListByPage(FindOutStoreDto findOutStoreDto) {

        //查询totalNum，总条数
        Integer totalNum = outStoreMapper.selectTotalNumByPage(findOutStoreDto);

        //创建page对象，并赋值
        Page page = new Page();
        page.setPageSize(findOutStoreDto.getPageSize());
        page.setPageNum(findOutStoreDto.getPageNum());
        page.setTotalNum(totalNum);
        page.setLimitIndex(page.getLimitIndex());
        page.setPageCount(page.getPageCount());

        //查询出库列表
        List<OutStoreVo> outStoreList = outStoreMapper.selectOutStoreListByPage(page, findOutStoreDto);

        //将查询结果赋值给page对象
        page.setResultList(outStoreList);

        //返回封装了分页对象的Result对象
        return Result.ok(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result outStoreConfirm(OutStore outStore) {

        //需要判断商品库存是否充足，如果不足，则返回错误信息
        Integer productInvent = productMapper.selectProductInventByProductId(outStore.getProductId());
        if (productInvent < outStore.getOutNum()) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品库存不足");
        }

        //设置出库单状态为已出库，并且更新操作员为当前操作员id
        Integer i = outStoreMapper.setIsOutIsOutStore(outStore);
        if (i <= 0) {
            log.info("未能设置出库单状态，受影响行数：{}", i);
            throw new BusinessException("系统繁忙，确认出库失败！");
        }
        //更新商品表中的库存数量（当前库存量-出库量）
        Integer j = outStoreMapper.deProductInvent(outStore);
        if (j <= 0) {
            log.info("未能更新商品表，受影响行数：{}", j);
            //手动回滚事务
            throw new BusinessException("系统繁忙，确认出库失败！");
        }

        return Result.ok("确认出库成功！");

    }
}
