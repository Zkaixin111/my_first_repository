package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //TODO:每次手动存放token比较麻烦，怎么解决。怎么指定传给前端的数据而不是全部传，
    // 在不创建新的实体类的前提下，有没有什么注解

    @PostMapping("/login")
    public Result login(@RequestBody User user){

        Result result = userService.login(user);
        return result;
    }
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }
    @PostMapping("/checkUserName")
    public Result checkUserName(@RequestParam("username") String name){
        Result result = userService.checkUserName(name);
        return  result;
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody User user){
        Result result = userService.regist(user);
        return result;
    }

}
