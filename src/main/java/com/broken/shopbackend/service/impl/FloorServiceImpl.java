package com.broken.shopbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.broken.shopbackend.domain.Floor;
import com.broken.shopbackend.mapper.FloorMapper;
import com.broken.shopbackend.service.FloorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements FloorService {

    @Override
    public List<Floor> listAll() {
        return baseMapper.selectList(null);
    }
}
