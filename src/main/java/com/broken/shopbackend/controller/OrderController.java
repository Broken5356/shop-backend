package com.broken.shopbackend.controller;

import com.broken.shopbackend.config.Constant;
import com.broken.shopbackend.config.HttpMsg;
import com.broken.shopbackend.domain.Order;
import com.broken.shopbackend.domain.R;
import com.broken.shopbackend.mapper.FlowersDao;
import com.broken.shopbackend.mapper.OrderDao;
import com.broken.shopbackend.mapper.UserDao;
import com.broken.shopbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户
 **/
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDao orderDao;

    @RequestMapping("/test")
    public R test() {
        R r = new R();
        return r.setCode(4000).setMsg(Constant.IMG_PATH).setData(orderDao.findAll(null));
    }

    @RequestMapping("/add")
    public R add(@RequestBody Order order) {
        R r = new R();
        float totalPrice = order.getAmount() * order.getOrderPrice();
        order.setPrice(totalPrice);
        int add = orderDao.add(order);
        if (add==1){
            return r.setCode(200).setMsg(HttpMsg.CREATE_ORDER_OK);
        }
        return r.setCode(400).setMsg(HttpMsg.CREATE_ORDER_FAILED);
    }

    //查询订单
    @RequestMapping("/getOrderList")
    public R getOrderList(){
        R r = new R();
        List<Order> orderList = orderService.getOrderList();
        if (StringUtil.isEmpty(orderList.toString())){
            return r.setCode(400).setMsg(HttpMsg.GET_ORDER_FAILED);
        }
        return r.setCode(200).setData(orderList).setMsg(HttpMsg.SUCCESSFULLY_ORDER);
    }

    @RequestMapping("/queryByAccount")
    public R queryByAccount(@RequestParam("account") String account) {
        R r = new R();
        if (StringUtil.isEmpty(account)) {
            return r.setCode(4000).setMsg(HttpMsg.INVALID_PARAM);
        }
        List<Order> orders = orderService.queryByAccount(account);
        return r.setCode(2000).setData(orders);
    }

    @RequestMapping("/find")
    public R find(@RequestParam("page") int page, @RequestParam("searchKey") String searchKey, @RequestParam("account") String account) {
        R r = new R();
        Map<String, Object> map = new HashMap<>();
        List<Order> orders = orderService.find(searchKey, account);
        if (orders == null) {
            return r.setCode(2000);
        }
        map.put("items", orders);
        map.put("len", orders.size());
        return r.setCode(2000).setData(map);
    }

    @RequestMapping("/findAll")
    public R findAll(@RequestParam("page") int page, @RequestParam("searchKey") String searchKey) {
        R r = new R();
        Map<String, Object> map = new HashMap<>();
        List<Order> orders = orderService.findAll(searchKey);
        if (orders == null) {
            return r.setCode(2000);
        }
        List<Order> items = orders.size() >= page * Constant.PAGE_SIZE ?
                orders.subList((page - 1) * Constant.PAGE_SIZE, page * Constant.PAGE_SIZE)
                : orders.subList((page - 1) * Constant.PAGE_SIZE, orders.size());
        int len = orders.size() % Constant.PAGE_SIZE == 0 ? orders.size() / Constant.PAGE_SIZE
                : (orders.size() / Constant.PAGE_SIZE + 1);
//        List<OrderVo> vos = new ArrayList<>();
//        for (Order item : items) {
//            User user = userDao.queryById(item.getUid());
//            OrderVo vo = new OrderVo();
//            vo.setAddress(user.getAddress()).setPhone(user.getPhone()).setUsername(user.getName())
//                    .setAmount(item.getAmount()).setGoodsName(item.getGoodsName()).setId(item.getId())
//                    .setUid(item.getUid()).setOrderGuid(item.getOrderGuid()).setPrice(item.getPrice())
//                    .setState(item.getState());
//            vos.add(vo);
//        }
//        map.put("items", vos);
        map.put("items", items);
        map.put("len", len);
        return r.setCode(2000).setData(map);
    }

    @RequestMapping("/update")
    public R update(@RequestBody Order order) {
        R r = new R();
        int ans = orderService.update(order);
        if (ans >= 0) {
            return r.setCode(2000).setMsg(HttpMsg.UPDATE_USER_OK);
        }
        return r.setCode(4000).setMsg(HttpMsg.UPDATE_USER_FAILED);
    }

    @RequestMapping("/changeState")
    public R changeState(@RequestBody Order order) {
        orderDao.changeState(order);
        return new R().setCode(2000).setMsg(HttpMsg.UPDATE_ORDER_OK);
    }

    @DeleteMapping("/delete")
    public R delete(@RequestParam("id") int id) {
        R r = new R();
        int ans = orderService.delete(id);
        if (ans == 1) {
            return r.setCode(2000).setMsg(HttpMsg.DELETE_USER_OK);
        }
        return r.setCode(4000).setMsg(HttpMsg.DELETE_USER_FAILED);
    }
}

