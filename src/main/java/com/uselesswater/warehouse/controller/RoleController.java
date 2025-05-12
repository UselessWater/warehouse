package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.Role;
import com.uselesswater.warehouse.beans.dto.AssignAuth;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.AuthService;
import com.uselesswater.warehouse.service.RoleService;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className: RoleController  @date 2025/5/6 0:40  @author UselessWater  @jdk_version 17
 *
 * @description 角色相关控制器
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private AuthService authService;

    /*查询所有角色接口*/
    @GetMapping("/role-list")
    public Result getAllRole(){
        return roleService.getAllRole();
    }

    /*分页获取角色列表*/
    @GetMapping("/role-page-list")
    public Result getRoleListByPage( Page page,  Role role){
        return roleService.findRoleListByPage(page,role);
    }

    /*添加角色*/
    @PostMapping("/role-add")
    public Result addRole(@RequestBody Role role,@RequestHeader("Token") String token){
        role.setCreateBy(jwtUtils.getCurrentUser(token).getUserId());
        return roleService.addRole(role);
    }

    /*启用角色*/
    @PutMapping("/role-state-update")
    public Result updateRoleState(@RequestBody Role role){
        return roleService.activeRole(role);
    }

    /*删除角色*/
    @DeleteMapping("/role-delete/{roleId}")
    public Result deleteRole(@PathVariable("roleId") Integer roleId){
        return roleService.removeRole(roleId);
    }

    /*修改角色信息，这里修改描述*/
    @PutMapping("/role-update")
    public Result updateRole(@RequestBody Role role,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        role.setUpdateBy(jwtUtils.getCurrentUser(token).getUserId());
        return roleService.modifyRole(role);
    }

    /*获取对应角色的权限菜单，这里前端只需要authIdList*/
    @GetMapping("/role-auth")
    public Result loadAssignedAuth(@RequestParam("roleId") Integer roleId){

        // 根据角色id获取对应的权限菜单
        List<Integer> authIdList = authService.findAssignedAuthIds(roleId);
        // 返回结果
        return Result.ok(authIdList);
    }

    /*提交为角色分配的权限*/
    @PutMapping("/auth-grant")
    public Result grantAuth(@RequestBody AssignAuth assignAuth){
        return authService.grantAuth(assignAuth);
    }


}
