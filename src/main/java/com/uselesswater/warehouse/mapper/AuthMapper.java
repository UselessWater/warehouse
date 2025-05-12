package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * className: AuthMapper  @date 2025/5/3 22:33  @author UselessWater  @jdk_version 17
 *
 * @description 权限菜单Mapper
 */

public interface AuthMapper {

    /**
     * 通过用户id（userId）查询权限菜单树
     * @param userId 用户id
     * @return 返回所有的权限菜单
     */
    List<Auth> selectAuthByUserId(@Param("userId") Integer userId);

    /**
     *  查询所有权限列表
     * @return 权限列表
     */
    List<Auth> selectAllAuth();

    /**
     *  根据角色id查询权限id列表
     * @param roleId 角色id
     * @return 权限id列表
     */
    List<Integer> selectAuthIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     *  根据角色ID删除权限
     * @param roleId 角色ID
     * @return 删除的权限数量
     */
    Integer deleteAuthByRoleId(@Param("roleId") Integer roleId);

    /**
     *  根据角色ID插入权限ID
     * @param authIds 权限ID列表
     * @param roleId 角色ID
     * @return 插入的权限ID数量,受影响行数
     */
    Integer insertAuthIdsByRoleId(@Param("authIds") List<Integer> authIds, @Param("roleId") Integer roleId);
}
