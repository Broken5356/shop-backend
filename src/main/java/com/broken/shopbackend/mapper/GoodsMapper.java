package com.broken.shopbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broken.shopbackend.domain.Goods;
import com.broken.shopbackend.domain.vo.GoodsDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 根据三级分类id查询商品列表
     *
     * @param catThreeId 三级分类id
     * @param page       分页对象
     * @return 商品列表
     */
    List<Goods> selectGoodsByCatThreeId(@Param("catThreeId") Integer catThreeId, Page<Goods> page);

    /**
     * 查询商品详情，包含图片和属性
     * @param goodsId 商品ID
     * @return 商品详情
     */
    GoodsDetailVO getGoodsDetail(@Param("goodsId") Integer goodsId);
}
