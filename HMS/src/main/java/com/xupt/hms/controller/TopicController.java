package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Topic;
import com.xupt.hms.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TopicController {
    @Resource
    private TopicService topicService;


    // 删除
    @RequestMapping("deleteTopic")
    public String topicDelete(Integer id, Model model) {
        model.addAttribute("isDelete", topicService.topicDelete(id));
        return "forward:listTopic";
    }

    // 修改
    @RequestMapping("modifyTopic")
    public String articleModify(Topic topic, Model model) {
        model.addAttribute("isModify", topicService.topicUpdate(topic));

        model.addAttribute("topic", topic);
        return "/admin/info/modifyTopic";
    }

    @RequestMapping("selectTopic")
    public String topicSelect(Integer id, Model model) {
        model.addAttribute("topic", topicService.SelectByID(id));
        return "/admin/info/modifyTopic";
    }

    // 查询 - 页面渲染
    @RequestMapping("listTopics")
    public String articleList(Model model,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "start", defaultValue = "1") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "topicid asc");
        // 2、从后端获取数据
        List<Topic> topic = topicService.topicList(name);
        // 3、对数据分页
        PageInfo<Topic> page = new PageInfo<>(topic);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("topics", topic);
        // 5、对下面视图进行渲染
        return "admin/info/topic";
    }

}
