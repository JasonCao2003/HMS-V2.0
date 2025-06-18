package com.xupt.hms.controller;

import com.xupt.hms.pojo.Admin;
import com.xupt.hms.pojo.Employ;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.AdminService;
import com.xupt.hms.service.EmployService;
import com.xupt.hms.service.UsersService;
import com.xupt.hms.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.Date;

@Controller
public class LoginController {

    @Resource
    private UsersService usersService;
    @Resource
    private EmployService employService;
    @Resource
    private AdminService adminService;

    @RequestMapping("/")
    public String hello() {
        return "login";
    }

    // 页面跳转
    @RequestMapping("login")
    public String goLogin() {
        return "login";
    }

    @RequestMapping("register")
    public String goRegister() {
        return "register";
    }


    // 生成验证码
    @RequestMapping("generateImageCode")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        // 1、生成四位随机数
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 2、保存在session作用域
        session.setAttribute("code", code);
        // 3、设置响应类型
        response.setContentType("image/png");
        // 4、获取输出流
        ServletOutputStream os = response.getOutputStream();
        // 5、根据随机数响应图片
        VerifyCodeUtils.outputImage(380, 55, os, code);
    }

    //用户注册
    @RequestMapping("userRegister")
    public String userRegister(Users user, String code, HttpSession session, Model model) {
        //1、判断用户输入验证码和session中的是否一致
        Object sessionCode = session.getAttribute("code").toString();
        if (!sessionCode.equals(code)) throw new RuntimeException("验证码错误");
        //2、用户注册
        Boolean isRegister = usersService.userAdd(user);
        model.addAttribute("isRegister", isRegister);
        Date currentdate = new Date();
        user.setRegdate(currentdate);
        if (isRegister) {
            return "/register";
        }
        return "/login";
    }

    //用户登录
    @RequestMapping("userLogin")
    public String userLogin(String username, String password, String code, HttpSession session, Model model) {
        try {
            // 1、判断用户输入的验证码和session中的验证码是否一致
            Object sessionCode = session.getAttribute("code").toString();
            if (!sessionCode.equals(code)) throw new RuntimeException("验证码错误");
            // 2、调用业务层进行用户登录
            Users user = usersService.userLogin(username, password);
            // 3、保存用户信息 --- 用于做访问验证
            session.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        // 4、跳转到主界面
        return "redirect:userhome";
    }

    //用户退出
    @RequestMapping("Logout")
    public String userLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    //雇员登录
    @RequestMapping("employLogin")
    public String employLogin(String username, String password, String code, HttpSession session, Model model) {
        try {
            // 1、判断用户输入的验证码和session中的验证码是否一致
            Object sessionCode = session.getAttribute("code").toString();
            if (!sessionCode.equals(code)) throw new RuntimeException("验证码错误");
            // 2、调用业务层进行用户登录
            Employ employ = employService.employLogin(username, password);
            // 3、保存用户信息
            session.setAttribute("employ", employ);
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        // 4、跳转到主界面
        return "employ/home";
    }

    //管理员登录
    @RequestMapping("adminLogin")
    public String adminLogin(String username, String password, HttpSession session) {
        try {
            // 2、调用业务层进行用户登录
            Admin admin = adminService.adminLogin(username, password);
            // 3、保存用户信息
            session.setAttribute("admin", admin);
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        // 4、跳转到主界面
        return "redirect:charset";
    }


    @RequestMapping("emailVerification")
    public String emailVerification(String email){
        Boolean end=usersService.useremail(email);
        if (!end){
            throw new RuntimeException();
        }
        return "login";
    }
}
