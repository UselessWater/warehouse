package com.uselesswater.warehouse.service;

import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.LoginUser;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;

import java.util.List;

/**
 * className: LoginService  @date 2025/5/1 23:13  @author UselessWater  @jdk_version 17
 *
 * @description 登录服务层
 */

public interface UserService {

    /**
     * 登录
     * @param loginUser 登录用户信息
     * @return 登录结果信息，包括状态码等
     */
    Result login(LoginUser loginUser);

    /**
     * 分页查询用户列表
     * @param page 页码对象，包含用户列表追加属性
     * @param user 用户对象
     * @return 返回页码对象
     */
    Page selectUserListByPage(Page page, User user);

    /**
     * 添加用户业务方法
     * @param user 用户信息对象
     * @return 结果对象
     */
    Result saveUser(User user);

    /**
     * 修改用户状态
     * @param user 用户对象
     * @return 返回结果
     */
    Result setStateByUserId(User user);

    /**
     *  根据用户ID列表删除用户
     * @param userIdList 用户ID列表
     * @return 删除结果
     */
    Result removeUserByUserIds(List<String> userIdList);

    /**
     *  更新用户信息
     * @param user 用户信息
     * @return 更新结果
     */
    Result updateUser(User user);

    /**
     *  初始化密码
     * @param userId 用户ID
     * @return 返回结果
     */
    Result initPassword(String userId);
}
