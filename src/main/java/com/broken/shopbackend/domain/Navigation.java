package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "shop_navigation")
public class Navigation {
    //导航id
    @TableId(type = IdType.AUTO)
    private Long id;

    //名称
    private String name;

    //图片
    private String imageSrc;

    //类型
    private String openType;

    //导航路径
    private String navigatorUrl;
}
