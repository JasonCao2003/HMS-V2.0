package com.xupt.hms.controller;

import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.UsersService;
import com.xupt.hms.utils.MailTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class EmailController {
    @Resource
    private UsersService usersService;

    @RequestMapping("emailIdentify")
    public String email(String password, Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        session.setAttribute("newPassword", password);
        user.setPassword(password);
        session.setAttribute("user", user);
        model.addAttribute("myuserinfo",user);
//        session.setAttribute("user", user);
//        model.addAttribute("myuserinfo",user);
        return "Utils/email";
    }
    //验证码
    @RequestMapping("/getCode")
    public boolean getCode(String email , HttpServletRequest request, HttpSession session, Model model){
        Users user = (Users) session.getAttribute("user");
        session.setAttribute("user", user);
        model.addAttribute("myuserinfo",user);
        //随机生成一个验证码
        Integer code=new Random().nextInt(500);
        request.getSession().setAttribute("code",code.toString());
        request.getSession().setAttribute("email",email);
        new Thread(
                () -> MailTool.send(email,code)
        ).start();
        return true;
    }

    // 修改用户密码
    @RequestMapping("/modifyUserPassword")
    public String tologin(String email, String code, HttpServletRequest request,
                          HttpSession session, Model model){
        String emailAddress = (String) request.getSession().getAttribute("email");
        String codeId = (String) request.getSession().getAttribute("code");

        if (email.equals(emailAddress) && code.equals(codeId)){
            Users user = (Users) session.getAttribute("user");
            usersService.userUpdate(user);
            session.setAttribute("user",user);
            model.addAttribute("myuserinfo",user);
            return "redirect:/selectUserInformation";
        }else {
            return "Utils/error";
        }
    }

}
