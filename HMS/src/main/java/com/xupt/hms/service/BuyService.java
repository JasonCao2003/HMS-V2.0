package com.xupt.hms.service;

import com.xupt.hms.pojo.Buy;

import java.util.List;

public interface BuyService {
    // 增加
    Boolean buyAdd(Buy buy);

    // 查询
    List<Buy> buyList(String goodsname);

    // 删除
    boolean buyDelete(Integer id);

    // 修改
    Boolean buyUpdate(Buy buy);

    // 通过id
    Buy selectById(Integer id);
}
