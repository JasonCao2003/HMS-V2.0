package com.xupt.hms.service;

import com.xupt.hms.pojo.Admin;

import java.util.List;

public interface AdminService {

    // 登录验证
    Admin adminLogin(String username, String password);

    // 增加
    Boolean adminAdd(Admin admin);

    // 删除
    boolean adminDelete(Integer id);

    // 修改
    Boolean adminUpdate(Admin admin);

    // 通过id查
    Admin selectById(Integer id);

    // 查询
    List<Admin> adminList(String name);
}
