package com.broken.shopbackend.controller;

import com.broken.shopbackend.domain.Goods;
import com.broken.shopbackend.domain.vo.GoodsDetailVO;
import com.broken.shopbackend.service.GoodsService;
import com.broken.shopbackend.util.PageResult;
import com.broken.shopbackend.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsComtroller {
    @Autowired
    private GoodsService goodsService;

    /**
     * 根据三级分类id查询商品列表
     *
     * @param catThreeId 三级分类id
     * @param pageNum    当前页
     * @param pageSize   总记录数
     * @return 商品列表
     */
    @GetMapping("/catThress/{catThreeId}")
    public ResponseResult getCatThreeId(@PathVariable Integer catThreeId,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<Goods> goodsByCatThreeId = goodsService.getGoodsByCatThreeId(catThreeId, pageNum, pageSize);
        return new ResponseResult(200, "获取成功", goodsByCatThreeId);
    }

    /**
     * 根据关键字查询商品列表
     *
     * @param query 关键字
     * @return 商品列表
     */
    @GetMapping("/search")
    public ResponseResult searchGoodsByName(@RequestParam(required = false) String query,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        String keyword = "";
        if (query != null && !query.trim().isEmpty()) {
            keyword = query.trim();
        }
        PageResult<Goods> goodsPageResult = goodsService.searchGoodsByName(keyword, pageNum, pageSize);
        return new ResponseResult(200, "获取成功", goodsPageResult);
    }

    /**
     * 查询商品详情，包含图片和属性
     * @param goodsId 商品ID
     * @return 商品详情
     */
    @GetMapping("/goodsDetail/{goodsId}")
    public ResponseResult<GoodsDetailVO> getGoodsDetail(@PathVariable("goodsId") Integer goodsId) {
        GoodsDetailVO goodsDetailVO = goodsService.getGoodsDetail(goodsId);
        return new ResponseResult(200,"获取成功",goodsDetailVO);
    }
}
