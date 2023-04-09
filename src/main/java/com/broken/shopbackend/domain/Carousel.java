package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "shop_carousel")
public class Carousel {
    //轮播图id
    @TableId(type = IdType.AUTO)
    private Long id;
    //图片
    private String imageSrc;
    //类型
    private String openType;
    //商品id
    private Long goodsId;
    //导航路径
    private String navigatorUrl;
}
