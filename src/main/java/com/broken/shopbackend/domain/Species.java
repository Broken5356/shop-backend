package com.broken.shopbackend.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 花朵种类实体类
 **/
@Data
@Accessors(chain = true)
public class Species {
    @TableId
    private int id;
    @TableField(value = "species_name")
    private String species_name;

}



