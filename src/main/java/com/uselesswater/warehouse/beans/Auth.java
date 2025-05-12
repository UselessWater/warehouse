package com.uselesswater.warehouse.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* 权限表
* @TableName auth_info
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer authId;
    private Integer parentId;
    private String authName;
    private String authDesc;
    private Integer authGrade;
    private String authType;
    private String authUrl;
    private String authCode;
    private Integer authOrder;
    private String authState;
    private Integer createBy;
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;

    /*-----------------追加属性-------------------*/
    //用于存放权限的列表
    private List<Auth> childAuth;

}
