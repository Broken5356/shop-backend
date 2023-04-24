package com.broken.shopbackend.service;

import com.broken.shopbackend.domain.Category;
import com.broken.shopbackend.domain.Species;

import java.util.List;


public interface SpeciesService {

    int add(Category category);
    int delete(int id);
    int update(Category category);
    List<Category> find(String searchKey);
    List<Category> findAll();
}
