package com.broken.shopbackend.mapper;

import com.broken.shopbackend.domain.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesDao {

    @Select("select * from shop_category where cat_name like concat('%',#{searchKey},'%') order by cat_level asc;")
    List<Category> find(String searchKey);

    @Select("select * from shop_category;")
    List<Category> findAll();

    @Update("update shop_category set cat_id=#{catId},cat_name = #{catName},cat_pid=#{catPid},cat_level=#{catLevel} where id = #{id};")
    int update(Category species);

    @Delete("delete from shop_category where id = #{id};")
    int delete(int id);

    @Insert("insert into shop_category(cat_id,cat_name,cat_pid,cat_level,cat_deleted) values(#{catId},#{catName},#{CatPid},#{catLevel},0);")
    int add(Category species);

}




