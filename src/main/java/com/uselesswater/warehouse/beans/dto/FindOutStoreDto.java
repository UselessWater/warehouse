package com.uselesswater.warehouse.beans.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * className: FindOutStoreDto  @date 2025/5/16 14:46  @author UselessWater  @jdk_version 17
 *
 * @description 用户封装出库单查询时的条件
 */
@Data
public class FindOutStoreDto implements Serializable {

    private static final long  serialVersionUID = 1L;

    private Integer storeId;
    private String productName;
    private LocalDate startTime;
    private LocalDate endTime;
    private String isOut;
    private Integer pageSize;
    private Integer pageNum;
    private Integer totalNum;

}
