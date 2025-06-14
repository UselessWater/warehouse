package com.uselesswater.warehouse.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 此User类只封装了用户的用户id、用户名和真实姓名
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userId;//用户id

    private String userCode;//用户名

    private String userName;//真实姓名
}
