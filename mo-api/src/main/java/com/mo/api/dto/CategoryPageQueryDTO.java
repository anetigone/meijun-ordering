package com.mo.api.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 分类分页查询条件
 */
@Data
public class CategoryPageQueryDTO implements Serializable {

    //页码
    private int page;
    //每页记录数
    private int pageSize;
    //分类名称
    private String name;
    //分类类型 1菜品分类  2套餐分类
    private Integer type;

}