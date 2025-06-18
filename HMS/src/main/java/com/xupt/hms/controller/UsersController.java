package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class UsersController {

    @Resource
    private UsersService usersService;

    // 用户界面
    @RequestMapping("userhome")
    public String goUserHome() {
        return "/user/home";
    }

    // 增加 - 跳转至增加界面
    @RequestMapping("goAddUser")
    public String goAddUser() {
        return "/admin/account/addUser";
    }

    // 增加
    @RequestMapping("addUser")
    public String addUser(Users user, Model model) {
        model.addAttribute("isAdd", usersService.userAdd(user));
        return "admin/account/addUser";
    }

    // 删除
    @RequestMapping("deleteUser")
    public String userDelete(Integer id, Model model) {
        model.addAttribute("isDelete", usersService.userDelete(id));
        return "forward:listUsers";
    }

    // 修改 - 查找修改信息
    @RequestMapping("selectUser")
    public String userSelect(Integer id, Model model) {
        model.addAttribute("user", usersService.selectById(id));
        return "/admin/account/modifyUser";
    }

    // 修改
    @RequestMapping("modifyUser")
    public String userModify(Users user, Model model) {
        model.addAttribute("isModify", usersService.userUpdate(user));
        model.addAttribute("user", user);
        return "/admin/account/modifyUser";
    }


    // 查询 - 页面渲染
    @RequestMapping("listUsers")
    public String userList(Model model,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "start", defaultValue = "1") int start,
                           @RequestParam(value = "size", defaultValue = "5") int size) {
        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "usersid asc");
        // 2、从后端获取数据
        List<Users> users = usersService.userList(name);
        // 3、对数据分页
        PageInfo<Users> page = new PageInfo<>(users);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("users", users);
        // 5、对下面视图进行渲染
        return "admin/account/users";
    }
}
