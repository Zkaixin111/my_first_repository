package com.atguigu.pojo.VO;

import lombok.Data;

@Data
public class PortalVo {
    
    private String keyWords;
    private Integer type;
    private Integer pageNum ;
    private Integer pageSize ;
}