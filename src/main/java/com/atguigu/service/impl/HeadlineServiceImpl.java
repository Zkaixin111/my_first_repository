package com.atguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.pojo.VO.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 29486
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2026-03-23 10:46:10
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;
    @Override
    public Result findNewsPage(PortalVo portalVo) {

        // 1. 构建查询条件
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()),
                        Headline::getTitle, portalVo.getKeyWords())
                .eq(portalVo.getType() != null, Headline::getType, portalVo.getType());

        // 2. 创建分页对象
        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        // 3. 执行分页查询（关键步骤）
        IPage<Headline> resultPage = headlineMapper.selectPage(page, queryWrapper);

        // 4. 封装分页信息
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", resultPage.getRecords());   // 当前页数据列表
        pageInfo.put("pageNum", resultPage.getCurrent());    // 当前页码
        pageInfo.put("pageSize", resultPage.getSize());      // 每页条数
        pageInfo.put("totalPage", resultPage.getPages());    // 总页数
        pageInfo.put("totalSize", resultPage.getTotal());    // 总记录数

        // 5. 返回结果
        return Result.ok(pageInfo);
    }
}




