package com.xupt.hms.controller;

import com.xupt.hms.pojo.*;
import com.xupt.hms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FrontController {

    @Resource
    private ArticleService articleService;
    @Resource
    private RoomsService roomsService;
    @Resource
    private RoomordersService roomOrderService;
    @Resource
    private FoodsService foodsService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private ComplainsService complainsService;


    @RequestMapping("goLightYear")
    public String goLightYear(){
        return "user/map";
    }


    @RequestMapping("myorderlist")
    public String goMyOrderList(HttpSession session,Model model){
        Users user= (Users) session.getAttribute("user");
        List<Orders> orders= ordersService.listByUserid(user.getUsersid());
        List<Roomorders> roomorders = roomOrderService.listByUserid(user.getUsersid());
        model.addAttribute("orders",orders);
        model.addAttribute("roomorders",roomorders);
        return "user/myorderlist";
    }


    @RequestMapping("cancelOrders")
    public String ordersDelete(Integer id, Model model) {
        model.addAttribute("isDelete", ordersService.ordersDelete(id));
        return "redirect:myorderlist";
    }

    @RequestMapping("cancelRoomOrders")
    public String roomOrdersDelete(Integer id, Model model){
        model.addAttribute("isDelete", roomOrderService.roomordersDelete(id));
        return "redirect:myorderlist";
    }

    // 展示新闻
    @RequestMapping("frontArticleLists")
    public String articleList(Model model) {
        // 1、从后端获取数据
        List<Article> article = articleService.articleList("");
        // 2、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("articles",article);
        // 3、对下面视图进行渲染
        return "user/article";
    }

    // 展示房间信息
    @RequestMapping("frontRoomsLists")
    public String roomList(Model model) {
        // 1、从后端获取数据
        List<Rooms> rooms = roomsService.roomsList("");
        // 3、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("rooms",rooms);
        // 4、对下面视图进行渲染
        return "user/rooms";
    }

    @RequestMapping("frontFoodsLists")
    public String foodList(Model model) {
        // 1、从后端获取数据
        List<Foods> foods = foodsService.foodsList("");
        // 3、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("foods",foods);
        // 4、对下面视图进行渲染
        return "user/foods";
    }

    @RequestMapping("newComplain")
    public String addNewComplain(Complains complains,HttpSession session){
        // 2、从session中获取到用户ID
        Users users = (Users) session.getAttribute("user");
        complains.setUsersid(users.getUsersid());
        // 4、时间戳生成添加时间
        Date currentDate = new Date();
        complains.setAddtime(currentDate);
        // 5、默认设置status为未确认
        complains.setStatus("未处理");
        complains.setReps("未回复");
        complainsService.complainsAdd(complains);
        return "redirect:goUserComplains";
    }
    @RequestMapping("goUserComplains")
    public String message() {return "/user/complains";}
}
