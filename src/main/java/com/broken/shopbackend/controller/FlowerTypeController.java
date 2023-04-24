package com.broken.shopbackend.controller;

import com.broken.shopbackend.config.Constant;
import com.broken.shopbackend.config.HttpMsg;
import com.broken.shopbackend.domain.Category;
import com.broken.shopbackend.domain.R;
import com.broken.shopbackend.domain.Species;
import com.broken.shopbackend.mapper.SpeciesDao;
import com.broken.shopbackend.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品分类
 **/
@RestController
@RequestMapping("species")
public class FlowerTypeController {
    @Autowired
    SpeciesService speciesService;
    @Autowired
    SpeciesDao dao;

    @RequestMapping("/test")
    R test() {
        R r = new R();
        return r.setCode(4000).setMsg(Constant.IMG_PATH).setData(dao.findAll());
    }

    @RequestMapping("/findAll")
    public R findAll() {
        R r = new R();
        List<Category> all = speciesService.findAll();
        if (all.size()<=0){
            return r.setCode(4000).setMsg(HttpMsg.NO_TYPE_NOW);
        }
        return r.setCode(2000).setData(all);
    }



    @RequestMapping("/find")
    public R find(@RequestParam("page") int page, @RequestParam("searchKey") String searchKey) {
        R r = new R();
        Map<String, Object> map = new HashMap<>();
        List<Category> list = speciesService.find(searchKey);
        if (list == null) {
            return r.setCode(2000);
        }
        List<Category> items = list.size() >= page * Constant.PAGE_SIZE ?
                list.subList((page - 1) * Constant.PAGE_SIZE, page * Constant.PAGE_SIZE)
                : list.subList((page - 1) * Constant.PAGE_SIZE, list.size());
        int len = list.size() % Constant.PAGE_SIZE == 0 ? list.size() / Constant.PAGE_SIZE
                : (list.size() / Constant.PAGE_SIZE + 1);
        map.put("items", items);
        map.put("len", len);
        return r.setCode(2000).setData(map);
    }


    @RequestMapping("/create")
    public R create(@RequestBody Category category) {
        R r = new R();
        try {
            speciesService.add(category);
            return r.setCode(2000).setMsg(HttpMsg.ADD_TYPE_OK);
        } catch (Exception e) {
            return r.setCode(4000).setMsg(HttpMsg.ADD_TYPE_FAILED);
        }
    }

    @RequestMapping("/update")
    public R update(@RequestBody Category category) {
        R r = new R();
        try {
            speciesService.update(category);
            return r.setCode(2000).setMsg(HttpMsg.UPDATE_TYPE_OK);
        } catch (Exception e) {
            return r.setCode(4000).setMsg(HttpMsg.UPDATE_TYPE_FAILED);
        }
    }


    @DeleteMapping("/delete")
    public R delete(@RequestParam("id") int id) {
        R r = new R();
        try {
            speciesService.delete(id);
            return r.setCode(2000).setMsg(HttpMsg.DELETE_TYPE_OK);
        } catch (Exception e) {
            return r.setCode(4000).setMsg(HttpMsg.DELETE_TYPE_FAILED);
        }
    }
}

