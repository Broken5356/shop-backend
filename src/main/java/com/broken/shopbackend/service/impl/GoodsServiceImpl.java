package com.broken.shopbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broken.shopbackend.domain.Goods;
import com.broken.shopbackend.domain.GoodsPics;
import com.broken.shopbackend.domain.vo.GoodsDetailVO;
import com.broken.shopbackend.mapper.GoodsMapper;
import com.broken.shopbackend.mapper.GoodsPicsMapper;
import com.broken.shopbackend.service.GoodsService;
import com.broken.shopbackend.util.PageResult;
import com.broken.shopbackend.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsPicsMapper goodsPicsMapper;

    @Override
    public PageResult<Goods> getGoodsByCatThreeId(Integer catThreeId, Integer pageNum, Integer pageSize) {
        //获得商品列表数
        List<Goods> goodsList = getByCatThreeId(catThreeId);
        // 构造分页结果对象
        PageResult<Goods> pageResult = new PageResult<>();
        pageResult.setList(goodsList);
        pageResult.setTotal((long) pageResult.getList().size());
        pageResult.setPageNum(pageNum);
        return pageResult;
    }

    //根据三级分类id获取商品列表
    @Override
    public List<Goods> getByCatThreeId(Integer catThreeId) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getCatThreeId, catThreeId);
        return goodsMapper.selectList(queryWrapper);
    }

    /**
     * 根据关键字查询商品列表
     * @param keyword  关键字
     * @return  返回商品列表
     */
    @Override
    public PageResult<Goods> searchGoodsByName(String keyword, Integer pageNum, Integer pageSize) {
        //获取商品列表
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Goods::getGoodsName,keyword)
                .orderByAsc(Goods::getGoodsId);
        List<Goods> goodsList = goodsMapper.selectList(queryWrapper);

        //封装数据
        PageResult<Goods> pageResult = new PageResult<>();
        pageResult.setList(goodsList);
        pageResult.setTotal((long) pageResult.getList().size());
        pageResult.setPageNum(pageNum);
        return pageResult;
    }

    @Override
    public GoodsDetailVO getGoodsDetail(Integer goodsId) {
        //获取商品详情
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getGoodsId, goodsId);
        Goods goods = baseMapper.selectOne(wrapper);

        //获取商品图片
        LambdaQueryWrapper<GoodsPics> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsPics::getGoodsId, goodsId);
        List<GoodsPics> goodsPics = goodsPicsMapper.selectList(queryWrapper);

        //封装数据
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        goodsDetailVO.setGoodsPics(goodsPics);
        goodsDetailVO.setGoods(goods);
        return goodsDetailVO;
    }
}
