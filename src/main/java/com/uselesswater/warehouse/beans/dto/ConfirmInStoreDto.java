package com.uselesswater.warehouse.beans.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * className: ConfirmInStoreDto  @date 2025/5/13 12:24  @author UselessWater  @jdk_version 17
 *
 * @description 确认入库时的参数接收dto
 */
@Data
public class ConfirmInStoreDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer insId;
    private Integer productId;
    private String isIn;
    private Integer inNum;
}
