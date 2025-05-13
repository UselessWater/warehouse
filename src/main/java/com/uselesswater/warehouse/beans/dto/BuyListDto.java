package com.uselesswater.warehouse.beans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @TableName buy_list
 */
@Data
public class BuyListDto implements Serializable {
    private Integer buyId;

    private Integer productId;

    private Integer storeId;

    private String storeName;

    private Integer buyNum;

    private Integer factBuyNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyTime;

    private Integer supplyId;

    private Integer placeId;

    private String buyUser;

    private String phone;

    private String isIn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate endTime;
    private String productName;


    private static final long serialVersionUID = 1L;
}