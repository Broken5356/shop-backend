package com.broken.shopbackend.domain.vo;

import com.broken.shopbackend.domain.Floor;
import lombok.Data;

import java.util.List;

@Data
public class FloorVO {
    private Integer id;
    private String title;
    private String imageSrc;


    private List<ProductVO> productVOs;

    public FloorVO(Floor floors,List<ProductVO> productVOs){
        this.id=floors.getId();
        this.title= floors.getTitle();
        this.imageSrc=floors.getImageSrc();
        this.productVOs=productVOs;
    }
}
