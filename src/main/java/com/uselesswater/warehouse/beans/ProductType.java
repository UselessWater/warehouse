package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @TableName product_type
 */
@Data
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer typeId;

    private Integer parentId;

    private String typeCode;

    private String typeName;

    private String typeDesc;


    //追加属性
    private List<ProductType> childProductCategory;
}