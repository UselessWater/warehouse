package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.Role;
import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 30373
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2025-05-06 00:01:39
* @Entity com.uselesswater.warehouse.beans.Role
*/
public interface RoleMapper {
    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> selectAllRole();

    /**
     * 根据用户id查询用户-角色关系，只返回roleList
     * @param userId
     * @return
     */
    List<Role> selectRoleList(@Param("userId") String userId);

    /**
     * 根据角色名称查询roleId
     * @param roleCheckList
     * @return
     */
    List<Integer> selectRoleIdByRoleName(@Param("roleChecklist") List<String> roleCheckList);

    /**
     *  动态查询所有角色行数
     * @param role 角色
     * @return 角色行数
     */
    Integer selectAllRoleRows(@Param("role") Role role);

    /**
     *  根据分页信息和角色信息查询所有角色
     * @param page 分页信息
     * @param role 角色信息
     * @return 角色列表
     */
    List<Role> selectAllRoleByPage(@Param("page") Page page, @Param("role") Role role);

    /**
     *  根据角色编码或名称查询角色
     * @param role 角色对象
     * @return 角色列表
     */
    List<Role> selectRoleByCodeOrName(@Param("role") Role role);

    /**
     *  插入角色
     * @param role 角色对象
     * @return 插入结果
     */
    Integer insertRole(@Param("role") Role role);

    /**
     *  更新角色状态
     * @param role 角色对象
     * @return 更新结果
     */
    Integer updateRoleState(@Param("role") Role role);

    /**
     * 根据角色ID删除角色信息（仅仅是role表）
     * @param roleId 角色ID
     * @return 受影响行数
     */
    Integer deleteRoleByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID修改角色描述
     * @param role 角色对象
     * @return 受影响行数
     */
    Integer updateRoleByUserId(@Param("role") Role role);
}




