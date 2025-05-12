package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName brand
 */
@Data
public class Brand implements Serializable {
    private Integer brandId;

    private String brandName;

    private String brandLeter;

    private String brandDesc;

    private static final long serialVersionUID = 1L;

}