package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "shop_floor")
public class Floor {
    //楼层id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //标题
    private String title;
    //图片
    private String imageSrc;
//    private List<Product> productList;


}
