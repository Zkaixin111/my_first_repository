package com.atguigu.service.impl;

import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author 29486
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2026-03-23 10:46:10
*/
//TODO:缺乏账号和密码为空校验,token应该存储在会话

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;


    @Override
    public Result login(User user) {
        //1.判断用户是否存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());

        User loginUser = userMapper.selectOne(userLambdaQueryWrapper);

        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

//        if (loginUser.getUserPwd() == null){
//            return  Result.build(null,)
//        }
        //判断账号密码是否正确
        String encrypt = MD5Util.encrypt(user.getUserPwd());
        if (!Objects.equals(loginUser.getUserPwd(), encrypt)){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        //账号密码都正确就向用户传输token

        String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

        //3.登录成功返回jwt令牌
          Map map  = new HashMap<String,String>();
          map.put("token",token);

        return Result.ok(map);
    }

    @Override
    public Result getUserInfo( String token) {
//        if (token == null){
//            return Result.build(null,Re)
//        }
        //判断token是否过期
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        //查询对应的用户数据
        int userId = jwtHelper.getUserId(token).intValue();


        User user = userMapper.selectById(userId);

        //        判断是否为空
        if (user != null) {
            user.setUserPwd(null);
            Map data = new HashMap();
            data.put("loginUser",user);
            return Result.ok(data);

        }
        return Result.build(null,ResultCodeEnum.NOTLOGIN);
    }

    @Override
    public Result checkUserName( String name) {
         System.out.println(name);
        //直接从数据库中查询是否有相同的姓名
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,name);

        User user = userMapper.selectOne(userLambdaQueryWrapper);
        System.out.println(user);
        if (user != null){
            return Result.build("用户名占用",ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok("没有相同名字");
    }

    @Override
    public Result regist(User user) {

        if (user.getUsername().length() < 4 ){
            return Result.build("账号名称必须超过3位",ResultCodeEnum.PARAM_ERROR);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int rows = userMapper.insert(user);
        System.out.println("rows = " + rows);
        return Result.ok(null);
    }

}




