package com.xupt.hms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.EmployMapper;
import com.xupt.hms.pojo.Employ;
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
public class EmployServiceImpl implements EmployService {
    //自动注入
    private final EmployMapper employMapper;

    @Autowired
    public EmployServiceImpl(EmployMapper employ) {
        this.employMapper = employ;
    }

    @Override
    public Employ employLogin(String username, String password) {
        //1、根据用户名查询数据据之中是否有该用户
        QueryWrapper<Employ> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employname", username);
        Employ employ = employMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(employ)) throw new RuntimeException("用户名不存在");
        //2、比较密码
        String passwordSecret = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!employ.getPassword().equals(passwordSecret)) throw new RuntimeException("密码输入错误");
        return employ;
    }


    @Override
    public Boolean employAdd(Employ employ) {
        //1、根据用户名查询数据据之中是否有该用户
        QueryWrapper<Employ> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employname", employ.getEmployname());
        Employ employ1 = employMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(employ1)) {
            //2、密码加密
            employ.setPassword(DigestUtils.md5DigestAsHex(employ.getPassword().getBytes(StandardCharsets.UTF_8)));
            //3、注册用户
            employMapper.insert(employ);
            return true;
        }
        return false;
    }


    @Override
    public List<Employ> employList(String name) {
        QueryWrapper<Employ> queryWrapper = new QueryWrapper<>();
        List<Employ> employs = null;
        if (!Objects.equals(name, "")) {
            employs = employMapper.selectList(queryWrapper.eq("employname", name));
        } else {
            employs = employMapper.selectList(null);
        }
        return employs;
    }

    @Override
    public boolean employDelete(Integer id) {
        try {
            QueryWrapper<Employ> queryWrapper = new QueryWrapper<>();
            employMapper.delete(queryWrapper.eq("employid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean employUpdate(Employ employ) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Employ> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("employid", employ.getEmployid());
        // 2、密码加密
        employ.setPassword(DigestUtils.md5DigestAsHex(employ.getPassword().getBytes(StandardCharsets.UTF_8)));
        // 3、执行更新操作
        int rows = employMapper.update(employ, updateWrapper);
        return rows > 0;
    }


    @Override
    public Employ selectById(Integer id) {
        QueryWrapper<Employ> queryWrapper = new QueryWrapper<>();
        return employMapper.selectOne(queryWrapper.eq("employid", id));
    }
}
