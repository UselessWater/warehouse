package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName store
 */
@Data
public class Store implements Serializable {
    private Integer storeId;

    private String storeName;

    private String storeNum;

    private String storeAddress;

    private String concat;

    private String phone;

    private static final long serialVersionUID = 1L;
}