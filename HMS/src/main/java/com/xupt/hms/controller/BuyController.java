package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Buy;
import com.xupt.hms.service.BuyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BuyController {

    @Resource
    private BuyService buyService;


    // 增加 - 跳转至修改界面
    @RequestMapping("goAddBuy")
    public String goAddBuy() {
        return "admin/goods/addBuy";
    }

    // 增加
    @RequestMapping("addBuy")
    public String addBuy(Buy buy, Model model) {
        System.out.println(buy);
        model.addAttribute("isAdd", buyService.buyAdd(buy));
        return "admin/goods/addBuy";
    }

    // 删除
    @RequestMapping("deleteBuy")
    public String buyDelete(Integer id, Model model) {
        model.addAttribute("isDelete", buyService.buyDelete(id));
        return "forward:listBuy";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectBuy")
    public String buySelect(Integer id, Model model) {
        model.addAttribute("buy", buyService.selectById(id));
        return "admin/goods/modifyBuy";
    }

    // 修改
    @RequestMapping("modifyBuy")
    public String buyModify(Buy buy, Model model) {
        model.addAttribute("isModify", buyService.buyUpdate(buy));
        model.addAttribute("buy", buy);
        return "admin/goods/modifyBuy";
    }

    // 查询 - 页面渲染
    @RequestMapping("listBuy")
    public String buyList(Model model,
                          @RequestParam(value = "goodsname", defaultValue = "") String goodsname,
                          @RequestParam(value = "start", defaultValue = "1") int start,
                          @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "buyid asc");
        // 2、从后端获取数据
        List<Buy> buys = buyService.buyList(goodsname);
        // 3、对数据分页
        PageInfo<Buy> page = new PageInfo<>(buys);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("buys", buys);
        // 5、对下面视图进行渲染
        return "admin/goods/buy";
    }
}
