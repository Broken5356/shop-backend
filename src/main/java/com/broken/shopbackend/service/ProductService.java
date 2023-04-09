package com.broken.shopbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broken.shopbackend.domain.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<Product> listByFloorId(Integer floorId);
}
