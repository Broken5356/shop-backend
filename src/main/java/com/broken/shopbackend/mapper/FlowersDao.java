package com.broken.shopbackend.mapper;

import com.broken.shopbackend.domain.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowersDao {

    @Select("select * from shop_goods where goods_name like concat('%',#{searchKey},'%') " +
            "and species_name like concat('%',#{searchType},'%') and state = 1;")
    List<Goods> find(@Param("searchKey") String searchKey, @Param("searchType")String searchType);

    @Select("select * from shop_goods where goods_name like concat('%',#{searchKey},'%');")
    List<Goods> findAll(String searchKey);

    @Select("select price from shop_goods where id = #{fid};")
    Float queryPrice(int fid);

    @Update("update shop_goods set cat_three_id=#{catThreeId} , goods_name = #{goodsName}, goods_price = #{goodsPrice}, goods_number = #{goodsNumber} where goods_id = #{goodsId};")
    Integer update(Goods goods);

    @Update("update shop_goods set img_guid = #{img_guid} where goods_id = #{goodsId};")
    Integer updateImg(@Param("img_guid") String img_guid,@Param("id") Integer id);

    @Update("update shop_goods set state = ${state} where goods_id = #{goodsId};")
    Integer changeState(Goods goods);

    @Delete("delete from shop_goods where goods_id = #{goodsId};")
    Integer delete(int id);

    @Insert("insert into shop_goods(cat_three_id,goods_name,goods_price,goods_number,state) values(#{catThreeId},#{goodsName},${goodsPrice},#{goodsNumber},1);")
    Integer add(Goods goods);

}




