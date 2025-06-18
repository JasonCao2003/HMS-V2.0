package com.xupt.hms.service;

import com.xupt.hms.pojo.Foods;

import java.util.List;

public interface FoodsService {
    // 增加
    Boolean foodsAdd(Foods foods);

    // 查询
    List<Foods> foodsList(String foodsname);

    // 删除
    boolean foodsDelete(Integer id);

    // 修改
    Boolean foodsUpdate(Foods foods);

    // 通过id
    Foods selectById(Integer id);
}
