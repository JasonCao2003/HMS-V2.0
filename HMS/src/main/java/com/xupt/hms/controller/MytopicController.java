package com.xupt.hms.controller;

import com.xupt.hms.pojo.Topic;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MytopicController {
    @Resource
    private TopicService topicsService;

    // 查询 - 页面渲染
    @RequestMapping("listMyTopic")
    public String topicsList(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        List<Topic> topicsList = topicsService.listTopicByUserid(users.getUsersid());
        model.addAttribute("topics", topicsList);
        return "user/mytopics";
    }

    @RequestMapping("cancelTopic")
    public String cancelTopic(Integer id, Model model) {
        model.addAttribute("isDelete", topicsService.topicDelete(id));
        return "redirect:listMyTopic";
    }
}
