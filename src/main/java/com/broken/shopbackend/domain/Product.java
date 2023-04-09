package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "shop_product")
public class Product {
    //商品id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //商品名称
    private String name;
    //图片
    private String imageSrc;
    //图片宽度
    private Integer imageWidth;
    //类型
    private String openType;
    //导航路径
    private String navigatorUrl;
    //楼层id
    private Integer floorId;
}
