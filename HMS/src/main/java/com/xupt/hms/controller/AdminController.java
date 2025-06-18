package com.xupt.hms.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Admin;
import com.xupt.hms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class AdminController {

    @Resource
    private AdminService adminService;


    // 增加 - 页面跳转
    @RequestMapping("goAddAdmin")
    public String goAddAdmin() {
        return "/admin/account/addAdmin";
    }

    // 增加
    @RequestMapping("addAdmin")
    public String adminAdd(Admin admin, Model model) {
        System.out.println(admin);
        model.addAttribute("isAdd", adminService.adminAdd(admin));
        return "admin/account/addAdmin";
    }

    // 删除
    @RequestMapping("deleteAdmin")
    public String adminDelete(Integer id, Model model) {
        model.addAttribute("isDelete", adminService.adminDelete(id));
        return "forward:listAdmins";
    }

    // 修改 - 查找待修改界面
    @RequestMapping("selectAdmin")
    public String adminSelect(Integer id, Model model) {
        model.addAttribute("admin", adminService.selectById(id));
        return "/admin/account/modifyAdmin";
    }

    // 修改
    @RequestMapping("modifyAdmin")
    public String adminModify(Admin admin, Model model) {
        model.addAttribute("isModify", adminService.adminUpdate(admin));
        model.addAttribute("admin", admin);
        return "/admin/account/modifyAdmin";
    }

    // 查询 - 页面渲染
    @RequestMapping("listAdmins")
    public String adminList(Model model,
                            @RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "start", defaultValue = "1") int start,
                            @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "adminid asc");
        // 2、从后端获取数据
        List<Admin> admins = adminService.adminList(name);
        // 3、对数据分页
        PageInfo<Admin> page = new PageInfo<>(admins);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("admins", admins);
        // 5、对下面视图进行渲染
        return "admin/account/admin";
    }
}
