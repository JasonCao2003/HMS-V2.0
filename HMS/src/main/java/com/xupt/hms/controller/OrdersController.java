package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Orders;
import com.xupt.hms.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class OrdersController {

    @Resource
    private OrdersService ordersService;


    // 增加 - 跳转至修改界面
    @RequestMapping("goAddOrders")
    public String goAddEmploy() {
        return "admin/finance/addOrders";
    }

    // 增加
    @RequestMapping("addOrders")
    public String addOrders(Orders orders, Model model) {
//        System.out.println(orders);
        model.addAttribute("isAdd", ordersService.ordersAdd(orders));
        return "admin/finance/addOrders";
    }

    //
    @RequestMapping("deleteOrders")
    public String ordersDelete(Integer id, Model model) {
        model.addAttribute("isDelete", ordersService.ordersDelete(id));
        return "forward:listOrders";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectOrders")
    public String ordersSelect(Integer id, Model model) {
        model.addAttribute("orders", ordersService.selectById(id));
        return "/admin/finance/modifyOrders";
    }

    // 修改
    @RequestMapping("modifyOrders")
    public String ordersModify(Orders orders, Model model) {
        model.addAttribute("isModify", ordersService.ordersUpdate(orders));
        model.addAttribute("orders", orders);
        return "/admin/finance/modifyOrders";
    }

    // 查询 - 页面渲染
    @RequestMapping("listOrders")
    public String ordersList(Model model,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "start", defaultValue = "1") int start,
                             @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "ordersid asc");
        // 2、从后端获取数据
        List<Orders> orders = ordersService.ordersList(name);
        // 3、对数据分页
        PageInfo<Orders> page = new PageInfo<>(orders);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("orders", orders);
        // 5、对下面视图进行渲染
        return "admin/finance/orders";
    }
}
