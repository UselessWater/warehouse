package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.Auth;
import com.uselesswater.warehouse.beans.dto.AssignAuth;
import com.uselesswater.warehouse.beans.dto.Result;

import java.util.List;

/**
 * className: AuthService  @date 2025/5/3 22:34  @author UselessWater  @jdk_version 17
 *
 * @description 菜单相关业务
 */

public interface AuthService {

    /**
     * 通过用户id查询权限菜单树
     * @param userId 用户id
     * @return 权限菜单树
     */
    List<Auth> selectAuthByUserId(Integer userId);

    /**
     * 加载所有权限菜单树
     * @return 返回结果
     */
    Result getAllAuthTree();

    /**
     *  根据角色ID查找已分配的权限ID列表
     * @param roleId 角色ID
     * @return 已分配的权限ID列表
     */
    List<Integer> findAssignedAuthIds(Integer roleId);

    /**
     *  授予授权
     * @param assignAuth 授权信息
     * @return 授权结果
     */
    Result grantAuth(AssignAuth assignAuth);
}
