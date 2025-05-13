package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.dto.AssignRole;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.RoleMapper;
import com.uselesswater.warehouse.mapper.UserRoleMapper;
import com.uselesswater.warehouse.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * className: UserRoleServiceImpl  @date 2025/5/6 8:43  @author UselessWater  @jdk_version 17
 *
 * @description
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(RoleMapper roleMapper, UserRoleMapper userRoleMapper) {
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
    }

    //分配角色
    @Transactional//添加事务
    @Override
    public Result assignRole(AssignRole assignRole) {
        /*
         * 分析：方式一：
         *   1.根据前端发送来的角色名称，查询roleId。
         *   2.删除已分配的用户角色关系
         *   3.在user_role表中添加用户-角色关系记录
         *       1>查询当前用户已经分配的角色
         *       2>将当前用户已经分配的角色与前端发送来的角色列表进行比对
         *       3>将前端发送来的角色列表中不在当前用户已经分配的角色列表中的角色添加到user_role表中
         * */

        /*方式二：*/
        //根据roleName查询roleId
        List<Integer> roleIdList = roleMapper.selectRoleIdByRoleName(assignRole.getRoleCheckList());

        //删除已分配的用户角色关系
        Integer row = userRoleMapper.removeAssignedRole(assignRole.getUserId());

        if (row <= 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS, "分配角色失败！");
        }

        try {
            //遍历需要分配的角色roleId，然后给对应用户添加roleId
            for (Integer roleId : roleIdList) {
                //重新分配用户角色关系（添加）
                Integer row2 = userRoleMapper.assignRole(assignRole.getUserId(), roleId);
                if (row2 <= 0) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Result.err(Result.CODE_ERR_BUSINESS, "分配角色失败！");
                }
            }
        } catch (NullPointerException e) {
            log.info("分配角色失败：{}",e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS,"分配角色失败："+ e.getMessage());
        }
        return Result.ok("分配角色成功！");
    }
}
