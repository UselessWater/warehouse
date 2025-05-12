package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName place
 */
@Data
public class Place implements Serializable {
    private Integer placeId;

    private String placeName;

    private String placeNum;

    private String introduce;

    private String isDelete;

    private static final long serialVersionUID = 1L;
}