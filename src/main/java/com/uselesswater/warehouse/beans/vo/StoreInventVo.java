package com.uselesswater.warehouse.beans.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * className: StoreInventVo  @date 2025/5/16 18:25  @author UselessWater  @jdk_version 17
 *
 * @description 仓库库存对象
 */
@Data
public class StoreInventVo implements Serializable {

    private static final long serialVersionUID = 1L;//serialVersionUID;

    private Integer storeId;
    private String storeName;
    private Integer totalInvent;
}
