package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.VO.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 29486
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2026-03-23 10:46:10
* @Entity com.atguihu.pojo.Headline
*/
@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {

    //自定义分页查询方法

}




