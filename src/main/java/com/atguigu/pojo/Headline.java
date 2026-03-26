package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @TableName news_headline
 */

@Data
public class Headline {
    @TableId
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;


    private Date createTime;

    private Date updateTime;

    @Version
    @JsonIgnore
    private Integer version;

    @JsonIgnore
    private Integer isDeleted;
}