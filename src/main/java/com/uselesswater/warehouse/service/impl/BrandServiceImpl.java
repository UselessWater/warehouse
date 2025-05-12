package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.Brand;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.BrandMapper;
import com.uselesswater.warehouse.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30373
* @description 针对表【brand(品牌)】的数据库操作Service实现
* @createDate 2025-05-07 20:03:55
*/
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public Result getAllBrand() {
        //查询所有品牌
        List<Brand> brandList = brandMapper.selectAllBrand();
        return Result.ok(brandList);
    }
}
