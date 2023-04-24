package com.broken.shopbackend.service;

import com.broken.shopbackend.domain.Cart;

import java.util.List;


public interface CartService {

    int add(Cart cart);
    int delete(int uid);
    int update(Cart cart);
    List<Cart> find(String searchKey,String account);
    List<Cart> queryByAccount(String account);
}
