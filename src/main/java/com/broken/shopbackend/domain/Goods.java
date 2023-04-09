package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "shop_goods")
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer goodsId;
    private Integer catId;
    private String goodsName;
    private Double goodsPrice;
    private Integer goodsNumber;
    private Integer goodsWeight;
    private String goodsIntroduce;
    private String goodsBigLogo;
    private String goodsSmallLogo;
    private Integer addTime;
    private Integer updTime;
    private Integer hotNumber;
    private Boolean isPromote;
    private Integer catOneId;
    private Integer catTwoId;
    private Integer catThreeId;

//    @TableField(exist = false)
//    private List<Pics> pics;
//
//    @TableField(exist = false)
//    private List<Attrs> attrs;
}
