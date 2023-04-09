package com.broken.shopbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broken.shopbackend.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
