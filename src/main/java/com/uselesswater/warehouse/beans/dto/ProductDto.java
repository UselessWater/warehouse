package com.uselesswater.warehouse.beans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * className: ProductDto  @date 2025/5/8 0:30  @author UselessWater  @jdk_version 17
 *
 * @description 用于承载分页查询商品列表时的条件和商品对象属性值
 */
@Data
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;

    private Integer storeId;

    private Integer brandId;

    private String productName;

    private String productNum;

    private Integer productInvent;

    private Integer typeId;

    private Integer supplyId;

    private Integer placeId;

    private Integer unitId;

    private String introduce;

    private String upDownState;

    private BigDecimal inPrice;

    private BigDecimal salePrice;

    private BigDecimal memPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer createBy;

    private Integer updateBy;

    private String imgs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date suppDate;

    /*追加属性*/
    private String brandName;
    private String typeName;
    private String supplyName;
    private String placeName;
    private String isOverDate;
    private String storeName;
    private String unitName;


}
