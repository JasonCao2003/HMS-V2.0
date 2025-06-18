package com.xupt.hms.service;

import com.xupt.hms.pojo.Tabx;

import java.util.List;

public interface TabxService {
    // 增加
    Boolean tabxAdd(Tabx tabx);

    // 查询
    List<Tabx> tabxList(String tabxname);

    // 删除
    boolean tabxDelete(Integer id);

    // 修改
    Boolean tabxUpdate(Tabx tabx);

    // 通过id
    Tabx selectById(Integer id);
}
