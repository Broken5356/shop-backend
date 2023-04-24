package com.broken.shopbackend.service;

import com.broken.shopbackend.domain.Goods;

import java.util.List;


public interface FlowersService {

    int add(Goods flower);
    int delete(int id);
    int update(Goods flower);
    List<Goods> find(String searchKey, String searchType);
    List<Goods> findAll(String searchKey);
    int updateImg(String img_guid,Integer id);
}
