package com.atguigu.service;

import com.atguigu.pojo.User;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 29486
* @description 针对表【news_user】的数据库操作Service
* @createDate 2026-03-23 10:46:10
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String name);

    Result regist(User user);
}
