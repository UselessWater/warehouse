package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName role
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private String roleCode;

    private String roleState;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss")
    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    //追加属性，角色创建者的用户的roleCode
    private String getCode;


}