package com.broken.shopbackend.service.impl;

import com.broken.shopbackend.mapper.CarouselMapper;
import com.broken.shopbackend.domain.Carousel;
import com.broken.shopbackend.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> getCarouselList() {
        return carouselMapper.selectList(null);
    }
}
