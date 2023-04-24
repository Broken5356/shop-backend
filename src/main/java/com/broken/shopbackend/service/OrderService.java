package com.broken.shopbackend.service;

import com.broken.shopbackend.domain.Cart;
import com.broken.shopbackend.domain.Order;

import java.util.List;


public interface OrderService {

    int add(Order order);
    int delete(int uid);
    int update(Order order);
    List<Order> find(String searchKey, String account);
    List<Order> findAll(String searchKey);
    List<Order> queryByAccount(String account);

    List<Order> getOrderList();
}
