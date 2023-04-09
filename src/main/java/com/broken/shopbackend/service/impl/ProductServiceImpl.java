package com.broken.shopbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broken.shopbackend.domain.Product;
import com.broken.shopbackend.mapper.ProductMapper;
import com.broken.shopbackend.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
    @Override
    public List<Product> listByFloorId(Integer floorId) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getFloorId,floorId);
        return baseMapper.selectList(queryWrapper);
    }
}
