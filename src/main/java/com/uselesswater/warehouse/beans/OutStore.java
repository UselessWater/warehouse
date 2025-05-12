package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName out_store
 */
@Data
public class OutStore implements Serializable {
    private Integer outsId;

    private Integer productId;

    private Integer storeId;

    private Integer tallyId;

    private BigDecimal outPrice;//出库单价

    private Integer outNum;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String isOut;

    private static final long serialVersionUID = 1L;
}