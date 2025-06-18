package com.xupt.hms.service;

import com.xupt.hms.pojo.Hist;

import java.util.List;

public interface HistService {

    // 增加
    Boolean histAdd(Hist hist);

    // 查询
    List<Hist> histList(String name);

    // 删除
    boolean histDelete(Integer id);

    // 修改
    Boolean histUpdate(Hist hist);

    // 通过id
    Hist selectById(Integer id);

}
