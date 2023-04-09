package com.broken.shopbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broken.shopbackend.domain.Goods;
import com.broken.shopbackend.domain.vo.GoodsDetailVO;
import com.broken.shopbackend.util.PageResult;
import com.broken.shopbackend.util.ResponseResult;

import java.util.List;

public interface GoodsService extends IService<Goods> {
    List<Goods> getByCatThreeId(Integer catThreeId);

    /**
     * 根据三级分类id查询商品列表
     */
    PageResult<Goods> getGoodsByCatThreeId(Integer catThreeId, Integer pageNum, Integer pageSize);

    /**
     * 根据关键字查询商品列表
     */
    PageResult<Goods> searchGoodsByName(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 查询商品详情，包含图片和属性
     */
    GoodsDetailVO getGoodsDetail(Integer goodsId);
}
