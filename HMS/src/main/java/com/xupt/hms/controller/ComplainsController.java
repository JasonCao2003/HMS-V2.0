package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Complains;
import com.xupt.hms.service.ComplainsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ComplainsController {
    @Resource
    private ComplainsService complainsService;

    //跳转到添加页面
    @RequestMapping("goAddComplains")
    public String goAddArticle() {
        return "/admin/info/addComplains";
    }

    // 增加
    @RequestMapping("addComplains")
    public String addComplains(Complains complains, Model model) {
        model.addAttribute("isAdd", complainsService.complainsAdd(complains));
        return "admin/info/addComplains";
    }

    // 删除
    @RequestMapping("deleteComplains")
    public String ComplainsDelete(Integer id, Model model) {
        model.addAttribute("isDelete", complainsService.complainsDelete(id));
        return "forward:listComplains";
    }

    //查找  -- 用于返回待修改数据
    @RequestMapping("selectComplains")
    public String ComplainsSelect(Integer id, Model model) {
        model.addAttribute("complains", complainsService.SelectById(id));
        return "/admin/info/modifyComplains";
    }

    // 修改
    @RequestMapping("modifyComplains")
    public String ComplainsModify(Complains complains, Model model) {
        model.addAttribute("isModify", complainsService.complainsUpdate(complains));
        model.addAttribute("complains", complainsService.SelectById(complains.getComplainsid()));
        return "/admin/info/modifyComplains";
    }

    // 查询 - 页面渲染
    @RequestMapping("listComplains")
    public String ComplainsList(Model model,
                                @RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "start", defaultValue = "1") int start,
                                @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "complainsid asc");
        // 2、从后端获取数据
        List<Complains> complains = complainsService.complainsList(name);
        // 3、对数据分页
        PageInfo<Complains> page = new PageInfo<>(complains);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("complains", complains);
        // 5、对下面视图进行渲染
        return "admin/info/complains";
    }
}
