package com.broken.shopbackend.domain.vo;

import com.broken.shopbackend.domain.Goods;
import com.broken.shopbackend.domain.GoodsPics;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsDetailVO{

    //商品信息
    private Goods goods;

    //商品图片
    private List<GoodsPics> goodsPics;

//    private List<Attrs> attrs;
}
