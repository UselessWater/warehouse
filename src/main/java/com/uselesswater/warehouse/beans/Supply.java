package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName supply
 */
@Data
public class Supply implements Serializable {
    private Integer supplyId;

    private String supplyNum;

    private String supplyName;

    private String supplyIntroduce;

    private String concat;

    private String phone;

    private String address;

    private String isDelete;

    private static final long serialVersionUID = 1L;
}