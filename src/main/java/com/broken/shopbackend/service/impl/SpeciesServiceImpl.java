package com.broken.shopbackend.service.impl;

import com.broken.shopbackend.domain.Category;
import com.broken.shopbackend.domain.Species;
import com.broken.shopbackend.mapper.SpeciesDao;
import com.broken.shopbackend.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpeciesServiceImpl implements SpeciesService {

    @Autowired
    SpeciesDao speciesDao;

    @Override
    public int add(Category category) {
        return speciesDao.add(category);
    }

    @Override
    public int delete(int uid) {
        return speciesDao.delete(uid);
    }

    @Override
    public int update(Category category) {
        return speciesDao.update(category);
    }

    @Override
    public List<Category> find(String searchKey) {
        return speciesDao.find(searchKey);
    }

    @Override
    public List<Category> findAll() {
        return speciesDao.findAll();
    }
}
