package com.xupt.hms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.AdminMapper;
import com.xupt.hms.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper admin) {
        this.adminMapper = admin;
    }

    @Override
    public Admin adminLogin(String username, String password) {
        //1、根据用户名查询数据据之中是否有该用户
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(admin)) throw new RuntimeException("用户名不存在");
        //2、比较密码
        String passwordSecret = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!admin.getPassword().equals(passwordSecret)) throw new RuntimeException("密码输入错误");
        return admin;
    }


    @Override
    public Boolean adminAdd(Admin admin) {
        //1、根据用户名查询数据据之中是否有该用户
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername());
        Admin user1 = adminMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(user1)) {
            //2、密码加密
            admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8)));
            //3、注册用户
            adminMapper.insert(admin);
            return true;
        }
        return false;
    }


    @Override
    public List<Admin> adminList(String name) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        List<Admin> admins = null;
        if (!Objects.equals(name, "")) {
            admins = adminMapper.selectList(queryWrapper.eq("username", name));
        } else {
            admins = adminMapper.selectList(null);
        }
        return admins;
    }

    @Override
    public boolean adminDelete(Integer id) {
        try {
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            adminMapper.delete(queryWrapper.eq("adminid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean adminUpdate(Admin admin) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("adminid", admin.getAdminid());
        // 2、密码加密
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8)));
        // 3、执行更新操作
        int rows = adminMapper.update(admin, updateWrapper);
        return rows > 0;
    }


    @Override
    public Admin selectById(Integer id) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        return adminMapper.selectOne(queryWrapper.eq("adminid", id));
    }
}
