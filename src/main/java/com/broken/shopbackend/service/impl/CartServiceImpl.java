package com.broken.shopbackend.service.impl;

import com.broken.shopbackend.domain.Cart;
import com.broken.shopbackend.mapper.CartDao;
import com.broken.shopbackend.mapper.UserDao;
import com.broken.shopbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartdao;
    @Autowired
    UserDao userDao;

    @Override
    public int add(Cart cart) {
        int uid = userDao.queryIdByAccount(cart.getAccount());
        cart.setUid(uid);
        Cart cart1 = cartdao.checkIsAdded(cart);  //检查是否已添加
        if (cart1==null){
            return cartdao.add(cart);
        }else {
            return cartdao.addAmount(cart);
        }
    }

    @Override
    public int delete(int uid) {
        return cartdao.delete(uid);
    }

    @Override
    public int update(Cart cart) {
        return cartdao.update(cart);
    }

    @Override
    public List<Cart> find(String searchKey,String account) {
        return cartdao.find(searchKey,account);
    }

    @Override
    public List<Cart> queryByAccount(String account) {
        Integer uid = userDao.queryIdByAccount(account);
        return cartdao.queryByUid(uid);
    }


}
