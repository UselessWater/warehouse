package com.uselesswater.warehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.uselesswater.warehouse.beans.Auth;
import com.uselesswater.warehouse.beans.dto.AssignAuth;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.AuthMapper;
import com.uselesswater.warehouse.mapper.UserMapper;
import com.uselesswater.warehouse.mapper.UserRoleMapper;
import com.uselesswater.warehouse.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * className: AuthServiceImpl  @date 2025/5/3 22:34  @author UselessWater  @jdk_version 17
 *
 * @description
 */
@Service
public class AuthServiceImpl implements AuthService {

    //注入redis模版
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private AuthMapper authMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Auth> selectAuthByUserId(Integer userId) {
        /*
         * 分析：
         *   1.首先我们权限菜单树是不常常修改的，所以我们可以将用户id作为key，对应的菜单树作为value放入缓存redis中
         *   2.先查询缓存，如果没有缓存，则直接向数据库中查询权限菜单树.有缓存则返回缓存的值
         *       这里以authTree:userId 作为 redis 中缓存的菜单树的键
         * */

        //向redis中查询菜单树的缓存,如果有缓存则返回
        String authTreeString = redisTemplate.opsForValue().get("authTree:" + userId);
        if (StringUtils.hasText(authTreeString)) {
            List<Auth> authTreeList = JSON.parseArray(authTreeString, Auth.class);
            return authTreeList;
        }
        //如果没有缓存，则向数据库中查询权限菜单树

        //根据userId查询该用户下的所有菜单树列表(包括一级菜单，二级菜单等)
        List<Auth> allAuthTreeList = authMapper.selectAuthByUserId(userId);

        //将 所有菜单树列表 转换 为菜单树列表
        List<Auth> authTreeList = allAuthTreeToAuthTree(allAuthTreeList,0);

        //向redis中存入缓存
        redisTemplate.opsForValue().set("authTree:" + userId, JSON.toJSONString(authTreeList));

        return authTreeList;
    }

    @Override
    public Result getAllAuthTree() {

        //查询所有的权限菜单列表
        List<Auth> allAuthList = authMapper.selectAllAuth();

        //将所有的权限Auth列表转换为权限菜单树，递归转换
        List<Auth> authTreeList = allAuthTreeToAuthTree(allAuthList, 0);

        return Result.ok(authTreeList);
    }

    @Override
    public List<Integer> findAssignedAuthIds(Integer roleId) {
        //根据roleId查询所有的权限（菜单）ID
        return authMapper.selectAuthIdsByRoleId(roleId);
    }

    @Transactional
    @Override
    public Result grantAuth(AssignAuth assignAuth) {
        //根据roleId删除该角色下所有的权限
        Integer row1 = authMapper.deleteAuthByRoleId(assignAuth.getRoleId());
        if (row1 <= 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS, "授权失败！");
        }
        //根据roleId和authId插入新权限
        Integer row = authMapper.insertAuthIdsByRoleId(assignAuth.getAuthIds(), assignAuth.getRoleId());
        if (row <= 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.err(Result.CODE_ERR_BUSINESS, "授权失败！");
        }
        return Result.ok("授权成功！");
    }


    /*递归查询所有菜单的二级菜单*/
    private List<Auth> allAuthTreeToAuthTree(List<Auth> allAuthTreeList,Integer parentId) {

        //创建集合，用户存放一级菜单
        List<Auth> firstLevelAuthTreeList = new ArrayList<>();

        //根据pid查询出所有一级菜单
        for (Auth auth: allAuthTreeList){
            //将每个一级菜单添加到一级菜单树集合中，一级菜单的pid是0
            if (auth.getParentId().equals(parentId)) {
                firstLevelAuthTreeList.add(auth);
            }
        }

        //根据一级菜单authId查询二级菜单，也就是递归查询所有的菜单的二级菜单
        for (Auth firstLevelAuth : firstLevelAuthTreeList) {
            //递归查询二级菜单
            List<Auth> secondLevelList = allAuthTreeToAuthTree(allAuthTreeList, firstLevelAuth.getAuthId());
            firstLevelAuth.setChildAuth(secondLevelList);
        }

        return firstLevelAuthTreeList;
    }
}
