package com.uselesswater.warehouse.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * className: UserRoleMapper  @date 2025/5/6 8:40  @author UselessWater  @jdk_version 17
 *
 * @description 用户角色关系
 */

public interface UserRoleMapper {

    /**
     * 移除已经分配了的用户-角色关系
     * @param userId 用户ID
     */
    Integer removeAssignedRole(String userId);

    /**
     * 重新添加用户-角色关系，分配角色
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    Integer assignRole(@Param("userId") String userId, @Param("roleId") Integer roleId);

    /**
     *  根据角色ID删除用户-角色关系
     * @param roleId 角色ID
     * @return 删除结果
     */
    Integer deleteUserRoleByRoleId(@Param("roleId") Integer roleId);
}
