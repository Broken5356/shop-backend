package com.broken.shopbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.broken.shopbackend.domain.Category;

import java.util.List;

public interface CategoryService  {


    List<Category> listCategoriesWithChildren(Integer parentId);

}
