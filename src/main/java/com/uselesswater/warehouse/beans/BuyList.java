package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.util.Date;
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

    private Date buyTime;

    private Integer supplyId;

    private Integer placeId;

    private String buyUser;

    private String phone;

    private String isIn;

    private static final long serialVersionUID = 1L;
}