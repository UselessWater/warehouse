package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName buy_list
 */
@Data
public class BuyList implements Serializable {
    private Integer buyId;

    private Integer productId;

    private Integer storeId;

    private Integer buyNum;

    private Integer factBuyNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyTime;

    private Integer supplyId;

    private Integer placeId;

    private String buyUser;

    private String phone;

    private String isIn;

    private static final long serialVersionUID = 1L;
}