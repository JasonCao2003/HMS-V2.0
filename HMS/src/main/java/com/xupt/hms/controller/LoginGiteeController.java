package com.xupt.hms.controller;



import com.xupt.hms.pojo.PrividerToken;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.pojo.gitUser;
import com.xupt.hms.service.UsersService;
import com.xupt.hms.utils.GiteeAccessPrivider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginGiteeController {
    @Resource
    private PrividerToken prividerToken;

    @Resource
    private GiteeAccessPrivider accessPrivider;

    @Resource
    private UsersService usersService;

    @Resource
    private gitUser user;

    @RequestMapping("/callback")
    public String callback(HttpSession session, Model model, @RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        prividerToken.setClient_id("388cdaad68bd5ff2b46a47bbddd59161398a8163846d64c87fb270cb98b70380");
        prividerToken.setCode(code);
        prividerToken.setRedirect_uri("http://localhost:8080/callback");
        prividerToken.setState(state);
        prividerToken.setClient_Secret("3b5d5e2a6e9794ba6bab874dfe8e3eee42d7906fd17a418e88a6d475f4f48f84");
        String Token = accessPrivider.getToken(prividerToken);
        user = accessPrivider.getUser(Token);
        Users users = usersService.userLogin(user.getName(), "gitee");

//        if (users == null) {
//            users = new Users();
//            users.setUsername(user.getName());
//            users.setPassword("gitee");
//            usersService.userAdd(users);
//            users = usersService.userLogin(user.getName(), user.getLogin());
//        }
        session.setAttribute("user", users);
        model.addAttribute("myuserinfo", users);
        return "user/home";
    }
}
