package com.uselesswater.warehouse.beans.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * className: InStoreVo  @date 2025/5/13 11:26  @author UselessWater  @jdk_version 17
 *
 * @description 查询返回的InStore的vo对象
 */
@Data
public class InStoreVo implements Serializable {
    private Integer insId;

    private Integer productId;

    private String storeName;

    private String productName;

    private Integer inNum;

    private String inPrice;//product表

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String userCode;

    private String isIn;

    private static final long serialVersionUID = 1L;
}
