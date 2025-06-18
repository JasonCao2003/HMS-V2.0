package com.xupt.hms.controller;

import com.xupt.hms.pojo.Complains;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.ComplainsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class MycomplainsController {
    @Resource
    private ComplainsService complainsService;
/*    @RequestMapping("goUserInformation")
    public String goUserInformation(){
        return "user/information";
    }*/


    // 查询 - 页面渲染
    @RequestMapping("listMyComplains")
    public String complainsList(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        List<Complains> complainsList = complainsService.listComplainsByUserid(users.getUsersid());
        model.addAttribute("complains", complainsList);
        return "user/mycomplains";
    }

    @RequestMapping("cancelComplains")
    public String cancelComplains(Integer id, Model model) {
        model.addAttribute("isDelete", complainsService.complainsDelete(id));
        return "redirect:listMyComplains";
    }
}
