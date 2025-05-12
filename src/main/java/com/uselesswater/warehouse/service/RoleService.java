package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.Role;
import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import org.springframework.stereotype.Service;

/**
 * className: RoleService  @date 2025/5/6 0:05  @author UselessWater  @jdk_version 17
 *
 * @description 角色类业务接口类
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @return Result 返回所有角色列表
     */
    Result getAllRole();

    /**
     * 根据userId查询用户-角色关系（查询到用户已经分配了的角色）,由于前端只需要roleList，所以只需要返回roleList即可
     * @param userId 用户ID
     * @return Result 返回用户角色列表
     */
    Result getUserRoleList(String userId);

    /**
     *  根据分页信息和角色信息查询角色列表
     * @param page 分页信息
     * @param role 角色信息
     * @return 查询结果
     */
    Result findRoleListByPage(Page page, Role role);

    /**
     *  添加角色
     * @param role 角色对象
     * @return 返回结果
     */
    Result addRole(Role role);

    /**
     *  激活角色或者禁用角色，函数名可能有误解
     * @param role 角色对象
     * @return 返回结果
     */
    Result activeRole(Role role);

    /**
     *  根据角色ID删除角色
     * @param roleId 角色ID
     * @return 删除结果
     */
    Result removeRole(Integer roleId);

    /**
     *  修改角色(描述roleDesc)
     * @param role 角色对象
     * @return 修改结果
     */
    Result modifyRole(Role role);
}
