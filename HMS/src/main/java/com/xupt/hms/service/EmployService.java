package com.xupt.hms.service;

import com.xupt.hms.pojo.Employ;

import java.util.List;

public interface EmployService {

    // 登录验证
    Employ employLogin(String username, String password);

    // 增加
    Boolean employAdd(Employ employ);

    // 查询
    List<Employ> employList(String name);

    // 删除
    boolean employDelete(Integer id);

    // 修改
    Boolean employUpdate(Employ employ);

    // 通过id
    Employ selectById(Integer id);
}
