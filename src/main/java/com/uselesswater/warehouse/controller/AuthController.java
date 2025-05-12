package com.uselesswater.warehouse.controller;

import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * className: AuthController  @date 2025/5/7 5:54  @author UselessWater  @jdk_version 17
 *
 * @description 权限菜单接口
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    /*加载权限菜单树*/
    @GetMapping("/auth-tree")
    public Result loadAllAuthTree(){
        return authService.getAllAuthTree();
    }


}
