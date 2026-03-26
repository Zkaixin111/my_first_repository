package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @TableName news_type
 */

@Data
public class Type {
    private Integer tid;

    private String tname;

    @Version
    @JsonIgnore
    private Integer version;
    @JsonIgnore
    private Integer isDeleted;
}