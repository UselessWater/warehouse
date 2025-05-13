package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.Role;
import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.RoleMapper;
import com.uselesswater.warehouse.mapper.UserRoleMapper;
import com.uselesswater.warehouse.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * className: RoleServiceImpl  @date 2025/5/6 0:05  @author UselessWater  @jdk_version 17
 *
 * @description 角色业务实现类
 */
@Service
@CacheConfig(cacheNames = "com.uselesswater.warehouse.service.impl.RoleServiceImpl")//配置缓存配置,cacheNames是键的前缀，一般用注解CacheConfig标注的类的全名
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /*
    * 如何利用注解来配置redis缓存：
    *   1.在启动类上使用注解@EnableCaching开启缓存功能
    *   2.在带有需要缓存方法的类上配置缓存前缀等，使用注解：@CacheConfig
    *   3.在需要缓存的方法上使用注解：@Cacheable，可以配置key等值
    * */
    @Override
    @Cacheable(key = "'all:role'")//配置缓存，key是缓存的键，value是缓存的值
    public Result getAllRole() {
        //查询所有角色
        List<Role> roleList  = roleMapper.selectAllRole();
        return Result.ok(roleList);
    }

    @Override
    public Result getUserRoleList(String userId) {

        //根据userId查询用户-角色关系，只返回roleList
        List<Role> roleList = roleMapper.selectRoleList(userId);

        //响应结果
        return Result.ok(roleList);
    }

    @Override
    public Result findRoleListByPage(Page page, Role role) {
        //分页动态查询所有行数
        Integer totalNum = roleMapper.selectAllRoleRows(role);
        //计算page对象中的属性值
        page.setTotalNum(totalNum);
        page.setPageCount(page.getPageCount());
        page.setLimitIndex(page.getLimitIndex());

        //动态分页查询所有角色列表
        List<Role> roleList = roleMapper.selectAllRoleByPage(page,role);
        //给page对象中的角色列表属性赋值
        page.setResultList(roleList);
        return Result.ok(page);
    }

    @Override
    @CacheEvict(key = "'all:role'")//清除缓存，防止序列化id不一致导致异常
    public Result addRole(Role role) {

        //查询角色代码或者角色名是否存在
        List<Role> dbRole = roleMapper.selectRoleByCodeOrName(role);
        if(!dbRole.isEmpty()){
            return Result.err(Result.CODE_ERR_BUSINESS,"角色代码或角色名已存在！");
        }

        //角色代码或角色不存在，则可以添加角色
        Integer row = roleMapper.insertRole(role);
        if (row > 0) {
            return Result.ok("添加角色成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "添加角色失败！");
    }

    @Override
    @CacheEvict(key = "'all:role'")
    public Result activeRole(Role role) {
        //更新角色状态，启用或者激活角色状态
        Integer row = roleMapper.updateRoleState(role);
        if (row > 0) {
            return Result.ok("启用或禁用角色成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "启用或禁用角色失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务
    @CacheEvict(key = "'all:role'")//清除缓存
    public Result removeRole(Integer roleId) {
        //删除角色表中的信息
        Integer row = roleMapper.deleteRoleByRoleId(roleId);
        if (row <= 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS, "删除角色失败！");
        }

        //删除角色-用户关系表中的信息
        Integer row2 = userRoleMapper.deleteUserRoleByRoleId(roleId);

        if (row2 <= 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS, "删除角色失败！");
        }

        return Result.ok("删除角色成功！");
    }

    @Override
    @CacheEvict(key = "'all:role'")
    public Result modifyRole(Role role) {
        //跟新角色，通过用户ID
        Integer row = roleMapper.updateRoleByUserId(role);
        if (row > 0) {
            return Result.ok("修改角色成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改角色失败！");
    }
}
