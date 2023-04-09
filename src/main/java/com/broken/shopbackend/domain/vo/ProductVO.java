package com.broken.shopbackend.domain.vo;

import com.broken.shopbackend.domain.Product;
import lombok.Data;

@Data
public class ProductVO {
    private Integer id;
    private String name;
    private String imageSrc;
    private Integer imageWidth;
    private String openType;
    private String navigatorUrl;

    public ProductVO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.imageSrc = product.getImageSrc();
        this.imageWidth = product.getImageWidth();
        this.openType = product.getOpenType();
        this.navigatorUrl = product.getNavigatorUrl();
    }
}
