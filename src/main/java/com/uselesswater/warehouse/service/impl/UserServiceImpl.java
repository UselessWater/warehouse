package com.uselesswater.warehouse.service.impl;

import com.uselesswater.warehouse.beans.User;
import com.uselesswater.warehouse.beans.dto.LoginUser;
import com.uselesswater.warehouse.beans.dto.Page;
import com.uselesswater.warehouse.beans.dto.Result;
import com.uselesswater.warehouse.mapper.UserMapper;
import com.uselesswater.warehouse.service.UserService;
import com.uselesswater.warehouse.utils.CurrentUser;
import com.uselesswater.warehouse.utils.DigestUtil;
import com.uselesswater.warehouse.utils.JwtUtils;
import com.uselesswater.warehouse.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * className: LoginServiceImpl  @date 2025/5/1 23:14  @author UselessWater  @jdk_version 17
 *
 * @description 实现类
 */
@Service
public class UserServiceImpl implements UserService {


    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtils jwtUtils;

    /*
     * 1>获取前端传来的验证码，然后与redis中的验证码比较，如果相同则验证码通过，不同则不通过。
     * 2>如果验证码通过，则进行用户名密码验证，如果通过则登录成功，返回token，不通过则登录失败。
     * 3>需要做请求拦截策略，当所有除了白名单的请求到达时，都拦截，除非携带token，正确。
     * */
    @Override
    public Result login(LoginUser loginUser) {

        //获取前端发来的验证码
        String verificationCode = loginUser.getVerificationCode();

        //获取存入redis中的验证码,判断是否存在key""
        if (Boolean.FALSE.equals(redisTemplate.hasKey(verificationCode))) {
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码错误");
        }

        //获取用户名和密码对象
        User user = userMapper.selectUserByCode(loginUser.getUserCode());

        if (user == null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "用户名不存在");
        }

        //判断用户如果未审核
        if (user.getUserState().equals(WarehouseConstants.USER_STATE_NOT_PASS)) {
            return Result.err(Result.CODE_ERR_BUSINESS, "用户未审核");
        }

        //如果用户审核通过，则判断用户密码是否正确
        if (DigestUtil.hmacSign(loginUser.getUserPwd()).equals(user.getUserPwd())) {
            //生成token
            CurrentUser currentUser = new CurrentUser();
            currentUser.setUserId(user.getUserId());
            currentUser.setUserCode(user.getUserCode());
            currentUser.setUserName(user.getUserName());
            String token = jwtUtils.loginSign(currentUser, user.getUserPwd());

            return Result.ok("登录成功", token);
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "用户名或密码错误,或用户未审核");
    }

    //分页查询用户列表
    @Override
    public Page selectUserListByPage(Page page, User user) {

        //获取总记录数，动态获取，条件可能为user对象中的参数
        Integer totalNum = userMapper.selectRowCount(user);

        //给总列表条数赋值
        page.setTotalNum(totalNum);

        //计算页码总数，并赋值给page对象
        page.setPageCount(page.getPageCount());
        //计算起始页，并赋值给page对象
        page.setLimitIndex(page.getLimitIndex());

        //获取用户列表，动态获取，条件可能为user对象中的参数
        List<User> userList = userMapper.selectUserListByPage(page, user);

        //对userList对象中的数据做脱敏处理
        userList.forEach(noSecurityUser -> {
            /*
            * 注意这里密码置为null比置为""空串更安全，
            * 因为为null则不指向任何对象，会直接别gb
            * 回收，如果为空串，则会在常量池中，可能内
            * 存泄漏时，会导致密码泄露
            * */
            noSecurityUser.setUserPwd(null);
        });

        //将用户列表封装到page对象中
        page.setResultList(userList);

        return page;
    }

    @Override
    public Result saveUser(User user) {

        //判断数据库中是否存在userCode，如果存在直接返回错误结果
        User dbUser = userMapper.selectUserByCode(user.getUserCode());
        if (dbUser != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "用户名已存在!");
        }

        //给密码加密
        String password = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(password);


        //如果用户不存在，则向数据库添加用户，返回受影响行数
        Integer row = userMapper.insertUser(user);

        if (row > 0) {
            return Result.ok("添加用户成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "添加用户失败");
    }

    @Override
    public Result setStateByUserId(User user) {

        //启用或禁用用户状态
        Integer row = userMapper.updateStateByUserId(user.getUserId(),user.getUserState());
        if (row >0) {
            return Result.ok("启用或禁用用户成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "启用或禁用用户失败！");
    }

    @Transactional
    @Override
    public Result removeUserByUserIds(List<String> userIdList) {
        //删除用户表中的用户信息
        Integer row = userMapper.updateIsDeleteByUserIds(userIdList);
        //删除用户-角色表中的用户信息
        userMapper.deleteUserRoleByUserIds(userIdList);
        if (row > 0) {
            return Result.ok("删除用户成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "删除用户失败！");
    }

    @Override
    public Result updateUser(User user) {
        Integer row = userMapper.updateUserByUserId(user);
        if (row > 0) {
            return Result.ok("修改用户成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改用户失败！");
    }

    @Override
    public Result initPassword(String userId) {
        Integer row  = userMapper.updatePwd(userId,DigestUtil.hmacSign("123456"));
        if (row > 0) {
            return Result.ok("重置密码成功,初始密码为123456！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "重置密码失败！");
    }
}
