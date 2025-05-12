package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private Integer productId;

    private Integer storeId;

    private Integer brandId;

    private String productName;

    private String productNum;

    private Integer productInvent;

    private Integer typeId;

    private Integer supplyId;

    private Integer placeId;

    private Integer unitId;

    private String introduce;

    private String upDownState;

    private BigDecimal inPrice;

    private BigDecimal salePrice;

    private BigDecimal memPrice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private Integer createBy;

    private Integer updateBy;

    private String imgs;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date suppDate;

    private static final long serialVersionUID = 1L;
}