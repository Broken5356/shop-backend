package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "shop_goods_pics")
public class GoodsPics {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer goodsId;
    private String picsBig;
    private String picsMid;
    private String picsSma;
    private String picsBigUrl;
    private String picsMidUrl;
    private String picsSmaUrl;
}
