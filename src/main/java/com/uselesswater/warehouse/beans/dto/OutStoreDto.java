package com.uselesswater.warehouse.beans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * className: OutStoreDto  @date 2025/5/10 23:06  @author UselessWater  @jdk_version 17
 *
 * @description 出库单实体，前端载体
 */
@Data
public class OutStoreDto implements Serializable {
    private Integer outsId;

    private Integer productId;

    private Integer storeId;

    private Integer tallyId;

    private BigDecimal outPrice;//salePrice

    private BigDecimal salePrice;

    private Integer outNum;

    private Integer createBy;

    private String isOut;

    private static final long serialVersionUID = 1L;

    public void setOutPrice(){
        this.outPrice = this.getSalePrice();
    }
}
