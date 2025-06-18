package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Hist;
import com.xupt.hms.service.HistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HistController {

    @Resource
    private HistService histService;


    // 增加 - 跳转至修改界面
    @RequestMapping("goAddHist")
    public String goAddHist() {
        return "admin/finance/addHist";
    }

    // 增加
    @RequestMapping("addHist")
    public String addHist(Hist hist, Model model) {
        System.out.println(hist);
        model.addAttribute("isAdd", histService.histAdd(hist));
        return "admin/finance/addHist";
    }

    // 删除
    @RequestMapping("deleteHist")
    public String histDelete(Integer id, Model model) {
        model.addAttribute("isDelete", histService.histDelete(id));
        return "forward:listHist";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectHist")
    public String histSelect(Integer id, Model model) {
        model.addAttribute("hist", histService.selectById(id));
        return "/admin/finance/modifyHist";
    }

    // 修改
    @RequestMapping("modifyHist")
    public String histModify(Hist hist, Model model) {
        model.addAttribute("isModify", histService.histUpdate(hist));
        model.addAttribute("hist", hist);
        return "/admin/finance/modifyHist";
    }

    // 查询 - 页面渲染
    @RequestMapping("listHist")
    public String histList(Model model,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "start", defaultValue = "1") int start,
                           @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "histid asc");
        // 2、从后端获取数据
        List<Hist> hists = histService.histList(name);
        // 3、对数据分页
        PageInfo<Hist> page = new PageInfo<>(hists);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("hists", hists);
        // 5、对下面视图进行渲染
        return "admin/finance/hist";
    }
}
