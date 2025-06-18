package com.xupt.hms.service;

import com.xupt.hms.pojo.Users;

import java.util.List;

public interface UsersService {

    // 登录验证
    Users userLogin(String username, String password);

    // 用户注册
    Boolean userAdd(Users user);

    List<Users> userList(String name);

    boolean userDelete(Integer id);

    Boolean userUpdateImg(Users user);

    Boolean userUpdate(Users user);


    Users selectById(Integer id);

    Boolean useremail(String emailcode);
}
