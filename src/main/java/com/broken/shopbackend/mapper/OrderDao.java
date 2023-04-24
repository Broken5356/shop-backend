package com.broken.shopbackend.mapper;

import com.broken.shopbackend.domain.Cart;
import com.broken.shopbackend.domain.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    @Select("select * from orders where flower like concat('%',#{searchKey},'%')  and uid = #{uid};")
    List<Order> find(@Param("searchKey") String searchKey, @Param("uid") int uid);

    @Select("select * from orders where goods_name like concat('%',#{searchKey},'%');")
    List<Order> findAll(String searchKey);


    @Select("select * from orders where fid = #{fid} and uid = #{uid};")
    Order checkIsAdded(Order order);


    @Update("update orders set state = #{state} where id = #{id};")
    int changeState(Order order);

    @Select("select * from orders where uid = #{uid};")
    List<Order> queryByUid(int uid);

    @Update("update orders set name = #{name},password = #{password},phone = #{phone},address = #{address} where id = #{id};")
    int update(Order order);

    @Delete("delete from orders where id = #{id};")
    int delete(int id);

    @Insert("insert into orders(consignee,goods_name,goods_small_logo,amount,order_price,order_phone,consignee_addr,price,state) " +
            "values(#{consignee},#{goodsName},#{goodsSmallLogo} ,#{amount},#{orderPrice},#{orderPhone},#{consigneeAddr},#{price},0);")
    int add(Order order);

    @Select("select * from orders;")
    List<Order> getOrderList();
}




