package com.uselesswater.warehouse.beans.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * className: OutStoreVo  @date 2025/5/16 15:05  @author UselessWater  @jdk_version 17
 *
 * @description 封装分页查询出库列表数据
 */
@Data
public class OutStoreVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer outsId;

    private Integer productId;

    private Integer storeId;

    private Integer tallyId;

    private BigDecimal outPrice;//出库单价

    private Integer outNum;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

    private String isOut;

    private String storeName;
    private String productName;
    private String userCode;//创建者
    private String tallyCode;


}
