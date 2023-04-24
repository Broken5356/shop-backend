package com.broken.shopbackend.service.impl;

import com.broken.shopbackend.domain.Goods;
import com.broken.shopbackend.mapper.FlowersDao;
import com.broken.shopbackend.service.FlowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowersServiceImpl implements FlowersService {

    @Autowired
    FlowersDao flowersDao;
    @Override
    public int add(Goods goods) {
        return flowersDao.add(goods);
    }

    @Override
    public int delete(int id) {
        return flowersDao.delete(id);
    }

    @Override
    public int update(Goods goods) {
        return flowersDao.update(goods);
    }

    @Override
    public List<Goods> find(String searchKey,String searchType) {
        return flowersDao.find(searchKey,searchType);
    }

    @Override
    public List<Goods> findAll(String searchKey) {
        return flowersDao.findAll(searchKey);
    }

    @Override
    public int updateImg(String img_guid,Integer id) {
        return flowersDao.updateImg(img_guid,id);
    }
}
