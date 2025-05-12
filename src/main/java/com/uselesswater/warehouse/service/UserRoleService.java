package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.dto.AssignRole;
import com.uselesswater.warehouse.beans.dto.Result;

/**
 * className: UserRoleService  @date 2025/5/6 8:42  @author UselessWater  @jdk_version 17
 *
 * @description
 */

public interface UserRoleService {
    /**
     * 为用户分配角色
     * @param assignRole 分配角色对象
     * @return 返回结果
     */
    Result assignRole(AssignRole assignRole);
}
