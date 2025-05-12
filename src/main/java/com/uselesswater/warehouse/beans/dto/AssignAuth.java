package com.uselesswater.warehouse.beans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * className: AssignAuth  @date 2025/5/7 8:14  @author UselessWater  @jdk_version 17
 *
 * @description dto对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignAuth {
    //包含authId的雷彪
    private List<Integer> authIds;
    private Integer roleId;
}
