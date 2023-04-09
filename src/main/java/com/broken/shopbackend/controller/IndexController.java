package com.broken.shopbackend.controller;

import com.broken.shopbackend.domain.*;
import com.broken.shopbackend.domain.vo.FloorVO;
import com.broken.shopbackend.domain.vo.ProductVO;
import com.broken.shopbackend.service.*;
import com.broken.shopbackend.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/index")
@CrossOrigin(origins = "http://localhost:8080")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private NavigationService navigationService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //获取轮播图数据
    @GetMapping("/getCarouselList")
    public ResponseResult getSwiperPicture() {
        List<Carousel> carouselList = carouselService.getCarouselList();
        return new ResponseResult(200, "获取成功!", carouselList);
    }

    //获取导航数据
    @GetMapping("/getNavigationList")
    public ResponseResult getNavigationList() {
        List<Navigation> navigationList = navigationService.getNavigationList();
        return new ResponseResult(200, "获取成功", navigationList);
    }

    //获取楼层以及商品数据，返回VO对象给前端展示
    @GetMapping("/listFloors")
    public ResponseResult listFloors() {
        //获取楼层全部数据
        List<Floor> floors = floorService.listAll();
        //楼层VO对象
        List<FloorVO> floorVOs = new ArrayList<>();

        //遍历楼层数据
        for (Floor floor : floors) {
            //根据楼层id获取商品信息
            List<Product> products = productService.listByFloorId(floor.getId());
            //商品VO对象
            List<ProductVO> productVOs = new ArrayList<>();

            //遍历商品信息
            for (Product product : products) {
                //添加商品信息到productVOs对象上
                productVOs.add(new ProductVO(product));
            }

            //添加楼层数据到floorVO对象上
            floorVOs.add(new FloorVO(floor,productVOs));
        }
        return new ResponseResult<>(200, "获取成功", floorVOs);
    }

    /**
     * 获取分类及子分类
     * @param parentId 父类id
     * @return
     */
    @GetMapping("/withChildren")
    public ResponseResult listCategoriesWithChildren(@RequestParam(name = "parentId", defaultValue = "0") Integer parentId) {
        List<Category> categories = categoryService.listCategoriesWithChildren(parentId);
        return new ResponseResult<>(200,"获取成功",categories);
    }

    /*@GetMapping
    public List<Category> listCategories(){
        return categoryService.listCategories();
    }

    @GetMapping("/{catId}/sub")
    public List<Category> listSubCategories(@PathVariable Integer catId){
        return categoryService.listSubCategories(catId);
    }*/

}
