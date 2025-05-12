package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.AssignRole;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.UserMapper;
import com.uselesswater.warehouse.service.RoleService;
import com.uselesswater.warehouse.service.UserRoleService;
import com.uselesswater.warehouse.service.UserService;
import com.uselesswater.warehouse.utils.CurrentUser;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

/**
 * className: UserController  @date 2025/5/2 15:28  @author UselessWater  @jdk_version 17
 *
 * @description 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;


    /*分页查询用户列表接口*/
    @GetMapping("/user-list")
    public Result getUserList(Page page, User user) {
        //查询用户列表并返回,page对象
        page = userService.selectUserListByPage(page,user);
        return Result.ok(page);
    }

    /*添加用户接口*/
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {

        CurrentUser currentUser = jwtUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        user.setCreateBy(userId);
        return userService.saveUser(user);
    }

    /*根据用户id查询用户-角色（查询用户已经分配了的角色）*/
    @GetMapping("/user-role-list/{userId}")
    public Result getUserRoleList(@PathVariable("userId") String userId){
        return roleService.getUserRoleList(userId);
    }

    /*为用户分配角色接口参数：roleCheckList*/
    @PutMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRole assignRole){

        //调用userRoleService的assignRole方法，传入assignRole参数
        return userRoleService.assignRole(assignRole);
    }

    /*删除单个用户接口*/
    @DeleteMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable("userId") String userId){

        return userService.removeUserByUserIds(Collections.singletonList(userId));
    }

    /*删除多个用户接口*/
    @DeleteMapping("/deleteUserList")
    public Result deleteUsers(@RequestBody List<String> userIdList){
        return userService.removeUserByUserIds(userIdList);
    }

    /*修改用户信息接口*/
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        user.setUpdateBy(jwtUtils.getCurrentUser(token).getUserId());
        return userService.updateUser(user);
    }

    /*重置密码（初始化密码为123456）*/
    @PutMapping("/updatePwd/{userId}")
    public Result resetPassword(@PathVariable("userId") String userId){
        return userService.initPassword(userId);
    }


}
