package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName in_store
 */
@Data
public class InStore implements Serializable {
    private Integer insId;

    private Integer storeId;

    private Integer productId;

    private Integer inNum;

    private Integer createBy;

    private Date createTime;

    private String isIn;

    private static final long serialVersionUID = 1L;
}