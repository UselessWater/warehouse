package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.Place;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.PlaceMapper;
import com.uselesswater.warehouse.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 30373
* @description 针对表【place(产地)】的数据库操作Service实现
* @createDate 2025-05-07 20:04:46
*/
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceMapper placeMapper;

    public PlaceServiceImpl(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    @Override
    public Result getAllPlace() {
        //查询所有产地
        List<Place> placeList = placeMapper.selectAllPlace();
        return Result.ok(placeList);
    }
}
