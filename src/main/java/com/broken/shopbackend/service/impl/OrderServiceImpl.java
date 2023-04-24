package com.broken.shopbackend.service.impl;

import com.broken.shopbackend.domain.Cart;
import com.broken.shopbackend.domain.Order;
import com.broken.shopbackend.mapper.OrderDao;
import com.broken.shopbackend.mapper.UserDao;
import com.broken.shopbackend.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderdao;
    @Autowired
    UserDao userDao;

    @Override
    public int add(Order order) {
        return orderdao.add(order);
    }

    @Override
    public int delete(int uid) {
        return orderdao.delete(uid);
    }

    @Override
    public int update(Order order) {
        return orderdao.update(order);
    }

    @Override
    public List<Order> find(String searchKey,String account) {
        Integer uid = userDao.queryIdByAccount(account);
        return orderdao.find(searchKey,uid);
    }

    @Override
    public List<Order> findAll(String searchKey) {
        return orderdao.findAll(searchKey);
    }

    @Override
    public List<Order> queryByAccount(String account) {
        Integer uid = userDao.queryIdByAccount(account);
        return orderdao.queryByUid(uid);
    }

    @Override
    public List<Order> getOrderList() {
        return orderdao.getOrderList();
    }
}
