package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Employ;
import com.xupt.hms.service.EmployService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class EmployController {

    @Resource
    private EmployService employService;


    // 增加 - 跳转至修改界面
    @RequestMapping("goAddEmploy")
    public String goAddEmploy() {
        return "admin/account/addEmploy";
    }

    // 增加
    @RequestMapping("addEmploy")
    public String addUser(Employ employ, Model model) {
        System.out.println(employ);
        model.addAttribute("isAdd", employService.employAdd(employ));
        return "admin/account/addEmploy";
    }

    // 删除
    @RequestMapping("deleteEmploy")
    public String userDelete(Integer id, Model model) {
        model.addAttribute("isDelete", employService.employDelete(id));
        return "forward:listEmploys";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectEmploy")
    public String employSelect(Integer id, Model model) {
        model.addAttribute("employ", employService.selectById(id));
        return "/admin/account/modifyEmploy";
    }

    // 修改
    @RequestMapping("modifyEmploy")
    public String employModify(Employ employ, Model model) {
        model.addAttribute("isModify", employService.employUpdate(employ));
        model.addAttribute("employ", employ);
        return "/admin/account/modifyEmploy";
    }

    // 查询 - 页面渲染
    @RequestMapping("listEmploys")
    public String userList(Model model,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "start", defaultValue = "1") int start,
                           @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "employid asc");
        // 2、从后端获取数据
        List<Employ> employs = employService.employList(name);
        // 3、对数据分页
        PageInfo<Employ> page = new PageInfo<>(employs);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("employs", employs);
        // 5、对下面视图进行渲染
        return "admin/account/employ";
    }
}
