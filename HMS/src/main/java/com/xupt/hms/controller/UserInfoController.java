package com.xupt.hms.controller;

import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.UsersService;
import com.xupt.hms.utils.Base64Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Random;


@Controller
public class UserInfoController {
    @Resource
    private UsersService usersService;

    @RequestMapping("goCamera")
    public String goCamera() {
        return "Utils/camera";
    }

    @RequestMapping("/upload")
    public String upload(int id,String userId, String imgStr, Model model, HttpSession session){
        String basePath = "D:/Code/HMS/photo/person/";
        String filePath = basePath + userId + "/" + userId + ".jpg";
        Users user = usersService.selectById(id);
        user.setImgstr("person/" + userId + "/" + userId+".jpg");
        usersService.userUpdateImg(user);
        Base64Utils.createFile(imgStr, new File(filePath));
        filePath = "D:/Code/Python/FaceIdentify/face_db/" + userId + "/" + userId + " (" + new Random().nextInt(11) +").jpg";
        Base64Utils.createFile(imgStr, new File(filePath));
        // 页面渲染
        session.setAttribute("user",user);
        model.addAttribute("myuserinfo",user);
        return "redirect:selectUserInformation";
    }

    // 查询 - 页面渲染
    @RequestMapping("listUserInformation")
    public String usersList(Model model, HttpSession session) {
        Users myuserinfo = (Users) session.getAttribute("user");
        model.addAttribute("myuserinfo", myuserinfo);
        return "user/information";
    }

    // 修改 - 查找待修改界面
    @RequestMapping("selectUserInformation")
    public String userSelect(Model model, HttpSession session) {
        Users myuserinfo = (Users) session.getAttribute("user");
        model.addAttribute("myuserinfo", myuserinfo);

        return "user/information";
    }

    // 修改基础信息
    @RequestMapping("modifyUserInformation")
    public String userModify(Users newUser, String sex, HttpSession session, Model model) {
        Users users = (Users) session.getAttribute("user");

        users.setContact(newUser.getContact());
        users.setRealname(newUser.getRealname());
        int commaIndex = sex.indexOf(',');
        sex = sex.substring(commaIndex+1);
        users.setSex(sex);
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+sex);
        users.setBirthday(newUser.getBirthday());
        usersService.userUpdate(users);

        model.addAttribute("myuserinfo",users);
        session.setAttribute("user",users);
        return "redirect:selectUserInformation";
    }
}
