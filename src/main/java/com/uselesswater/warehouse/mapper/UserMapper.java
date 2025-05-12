package com.uselesswater.warehouse.mapper;

import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.LoginUser;
import com.uselesswater.warehouse.beans.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * className: LoginMapper  @date 2025/5/1 23:15  @author UselessWater  @jdk_version 17
 *
 * @description 登录相关
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param userCode 用户名
     * @return 返回用户信息
     */
    User selectUserByCode(@Param("userCode") String userCode);

    /**
     * 动态，查询总页码数
     * @param user 用户对象，包含了动态查询条件
     * @return
     */
    Integer selectRowCount(@Param("user") User user);

    /**
     * 根据用户对象条件，动态分页查询用户列表
     * @param page
     * @param user
     * @return
     */
    List<User> selectUserListByPage(@Param("page") Page page, @Param("user") User user);

    /**
     * 添加用户
     * @param user 用户信息对象
     * @return 返回受影响行数
     */
    Integer insertUser(@Param("user") User user);

    /**
     * 根据用户id修改用户状态
     * @param userId
     * @param userState
     * @return
     */
    Integer updateStateByUserId(@Param("userId") Integer userId, @Param("userState") String userState);

    /**
     *  根据用户ID列表更新删除状态
     * @param userIdList 用户ID列表
     * @return 更新结果
     */
    Integer updateIsDeleteByUserIds(@Param("userIdList") List<String> userIdList);

    /**
     *  根据用户ID列表删除用户-角色关系
     * @param userIdList 用户ID列表
     * @return 删除的用户角色数量
     */
    Integer deleteUserRoleByUserIds(@Param("userIdList") List<String> userIdList);

    /**
     *  根据用户ID更新用户信息
     * @param user 用户对象
     * @return 更新结果
     */
    Integer updateUserByUserId(@Param("user") User user);

    /**
     *  更新密码
     * @param userId 用户ID
     * @param passwordHash 密码哈希值
     * @return 更新结果
     */
    Integer updatePwd(@Param("userId") String userId, @Param("passwordHash") String passwordHash);
}
