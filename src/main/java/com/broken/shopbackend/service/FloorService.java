package com.broken.shopbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broken.shopbackend.domain.Floor;

import java.util.List;

public interface FloorService extends IService<Floor> {
    List<Floor> listAll();
}
