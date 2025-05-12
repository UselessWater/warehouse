package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Place;

import java.util.List;

/**
* @author 30373
* @description 针对表【place(产地)】的数据库操作Mapper
* @createDate 2025-05-07 20:04:46
* @Entity com.uselesswater.warehouse.beans.Place
*/
public interface PlaceMapper{

    /**
     *  查询所有地点
     * @return 产地 列表
     */
    List<Place> selectAllPlace();
}
