package com.broken.shopbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.broken.shopbackend.domain.Category;
import com.broken.shopbackend.mapper.CategoryMapper;
import com.broken.shopbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategoriesWithChildren(Integer parentId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getCatId, Category::getCatName, Category::getCatPid,
                        Category::getCatLevel, Category::getCatDeleted, Category::getCatIcon) //指定查询sql字段，去除children字段
                .eq(Category::getCatDeleted, false)  // 查询未删除的分类
                .eq(Category::getCatPid, parentId);

        // 查询指定分类下的所有子分类（包括子分类的子分类，递归查询）
        List<Category> children = categoryMapper.selectList(queryWrapper);
        for (Category child : children) {
            // 递归查询子分类的子分类
            child.setChildren(listCategoriesWithChildren(child.getCatId()));
        }
        return children;
    }

    /**
     * 查询所有一级分类
     */
   /* @Override
    public List<Category> listCategories() {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getCatDeleted, false)  // 查询未删除的分类
                .eq(Category::getCarLevel, 0)  // 查询一级分类
                .orderByAsc(Category::getCarId));   // 按照分类id升序排序
    }*/

    /**
     * 查询指定分类下的所有子分类（包括子分类的子分类，递归查询）
     */
    /*@Override
    public List<Category> listSubCategories(Integer catId) {
        List<Category> result = new ArrayList<>();
        getSubCategories(result, catId);
        return result;
    }*/

    /**
     * 递归查询指定分类下的所有子分类
     */
    private void getSubCategories(List<Category> result, Integer catId) {
        List<Category> subCategories = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getCatDeleted, false)  // 查询未删除的分类
                .eq(Category::getCatPid, catId)      // 查询指定分类下的子分类
                .orderByAsc(Category::getCatId));      // 按照分类id升序排序
        for (Category subCategory : subCategories) {
            result.add(subCategory);
            getSubCategories(result, subCategory.getCatId());  // 递归查询子分类的子分类
        }
    }


    /**
     * 查询指定分类下的所有子分类（包括子分类的子分类，递归查询）
     */
/*    @Override
    public List<Category> listSubCategories(Integer catId) {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getCatDeleted,false)      // 查询未删除的分类
                .and(wrapper->wrapper.eq(Category::getCarId,catId))
                .or().eq(Category::getCatPid,catId)
                .orderByAsc(Category::getCarId));
    }*/
}
