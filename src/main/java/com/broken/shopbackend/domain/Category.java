package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.List;

@Data
@TableName(value = "shop_category")
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //分类ID
    private Integer catId;
    //分类名称
    private String catName;
    //父类别ID
    private Integer CatPid;
    //父类级别
    private Integer catLevel;
    //是否删除
    private Boolean catDeleted;
    //类别图标
    private String catIcon;

    private List<Category> children;  // 添加children属性
}
