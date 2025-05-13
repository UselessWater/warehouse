package com.uselesswater.warehouse.beans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * className: InStoreDto  @date 2025/5/13 11:20  @author UselessWater  @jdk_version 17
 *
 * @description 分页获取所有入库信息的查询条件Dto
 */
@Data
public class InStoreDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer storeId;
    private String productName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;//和createTime相关计算判断
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;//和createTime相关计算判断
}
