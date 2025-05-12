package com.uselesswater.warehouse.controller;

import com.google.code.kaptcha.Producer;
import com.uselesswater.warehouse.beans.Auth;
import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.LoginUser;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.service.AuthService;
import com.uselesswater.warehouse.service.UserService;
import com.uselesswater.warehouse.utils.CurrentUser;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * className: LoginController  @date 2025/4/30 17:47  @author UselessWater  @jdk_version 17
 *
 * @description 登录相关控制器
 */
@RestController
public class LoginController {

    @Resource(name = "captchaProducer")
    private Producer producer;

    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    /*
     * 注意这里注入的StringRedisTemplate时有一个坑，
     * 我们使用resource时，会自动先根据变量名注入，所以，
     * 注入时变量名至关重要。我们也可以显示的指定注入的name值
     */
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private AuthService authService;

    /*验证码业务*/
    @GetMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) {

        ServletOutputStream out = null;

        try {
            //创建图片文本
            String text = producer.createText();

            //通过图片文本创建图片,返回缓冲的图片对象BufferedImage
            BufferedImage image = producer.createImage(text);

            //将图片文本存入redis，设置过期时间为3600秒
            redisTemplate.opsForValue().set(text, "", 3600, TimeUnit.SECONDS);

            //将图片响应
            //1.设置响应类型
            response.setContentType("image/jpeg");

            //获取响应字节输出流
            out = response.getOutputStream();

            //2.响应，通过字节输出流将图片响应给前端，ImageIO对象可以用于向前端直接写图片
            ImageIO.write(image, "jpg", out);

            //刷新缓冲区
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*登录业务*/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody LoginUser loginUser) {
        //调用登录业务
        return userService.login(loginUser);
    }

    /*获取当前登录的用户信息*/
    @GetMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //通过解析token获取当前登录的用户信息
        CurrentUser currentUser = jwtUtils.getCurrentUser(token);
        return Result.ok(currentUser);
    }

    /*加载权限菜单树*/
    @GetMapping("/user/auth-list")
    public Result authList(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = jwtUtils.getCurrentUser(token);
        Integer userId = currentUser.getUserId();
        List<Auth> authTreeList = authService.selectAuthByUserId(userId);
        return Result.ok(authTreeList);
    }

    /*退出登录*/
    @DeleteMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //删除redis中缓存的token
        redisTemplate.delete(token);
        return Result.ok("退出登录成功");
    }

    /*启用或者禁用用户*/
    @PutMapping("/user/updateState")
    public Result SetState(@RequestBody User user) {
        return userService.setStateByUserId(user);
    }



    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "test data";
    }
}
