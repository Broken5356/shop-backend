package com.broken.shopbackend.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broken.shopbackend.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> list;
    private Integer pageNum;

    public PageResult(Page<Goods> page) {
    }


}
