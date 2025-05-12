package com.uselesswater.warehouse.beans.dto;

import com.uselesswater.warehouse.beans.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * className: AssignRole  @date 2025/5/6 7:05  @author UselessWater  @jdk_version 17
 *
 * @description 分配角色传输dot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignRole implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户ID
    private String userId;
    // 用户编码
    private String userCode;//没用
    // 角色列表
    private List<Role> roleList;//无用参数
    // 角色选中列表
    private List<String> roleCheckList;
}
