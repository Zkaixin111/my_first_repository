package com.atguigu.controller;

import com.atguigu.mapper.HeadlineMapper;
import com.atguigu.pojo.Type;
import com.atguigu.pojo.VO.PortalVo;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("/findAllTypes")
    public Result findAllTypes(){
        List<Type> list = typeService.list();
        return  Result.ok(list);
    }
    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){

        System.out.println(portalVo);

        Result result = headlineService.findNewsPage(portalVo);

        return  result;

    }

}
