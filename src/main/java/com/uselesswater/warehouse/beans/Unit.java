package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName unit
 */
@Data
public class Unit implements Serializable {
    private Integer unitId;

    private String unitName;

    private String unitDesc;

    private static final long serialVersionUID = 1L;
}