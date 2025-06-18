package com.xupt.hms.controller;


import com.xupt.hms.pojo.Admin;
import com.xupt.hms.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
public class AdminInfoController {

    @Resource
    private AdminService adminService;

    // 查询 - 页面渲染
    @RequestMapping("listInformation")
    public String adminList(Model model, HttpSession session) {
        Admin myinfo = (Admin) session.getAttribute("admin");
        model.addAttribute("myinfo", myinfo);
        return "admin/information";
    }

    // 修改 - 查找待修改界面
    @RequestMapping("selectInformation")
    public String adminSelect(Model model, HttpSession session) {
        Admin myinfo = (Admin) session.getAttribute("admin");
        model.addAttribute("myinfo", myinfo);
        return "admin/information";
    }

    // 修改
    @RequestMapping("modifyInformation")
    public String adminModify(Admin admin, HttpSession session) {
        Admin myadmin = (Admin) session.getAttribute("admin");
        admin.setAdminid(myadmin.getAdminid());
        adminService.adminUpdate(admin);
        session.setAttribute("admin", admin);
        return "redirect:selectInformation";
    }
}
