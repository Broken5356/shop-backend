package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 演员实体类
 **/
@Data
@Accessors(chain = true)
public class Order {
    private Long id;
    //收货人
    private String consignee;
    private String orderGuid;
    //商品名称
    private String goodsName;
    //商品图片
    private String goodsSmallLogo;
    //数量
    private int amount;
    //单价
    private float orderPrice;
    //手机号
    private String orderPhone;
    //收获地址
    private String consigneeAddr;
    //总价
    private float price;
    //订单状态
    private float state;
    // 用户 id
    private int uid;
}



