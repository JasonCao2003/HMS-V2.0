package com.xupt.hms.controller;

import com.xupt.hms.pojo.Foods;
import com.xupt.hms.pojo.Orders;
import com.xupt.hms.pojo.Roomorders;
import com.xupt.hms.pojo.Users;
import com.xupt.hms.service.FoodsService;
import com.xupt.hms.service.OrdersService;
import com.xupt.hms.service.RoomordersService;
import com.xupt.hms.service.RoomsService;
import com.xupt.hms.utils.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Controller
public class PayController {


    private AlipayUtil alipayUtil;

    @Autowired
    public void setAlipayUtil(AlipayUtil alipayUtil) {
        this.alipayUtil = alipayUtil;
    }
    @Resource
    private RoomsService roomsService;
    @Resource
    private RoomordersService roomOrderService;
    @Resource
    private FoodsService foodsService;
    @Resource
    private OrdersService ordersService;




    @RequestMapping("goAliPay")
    public String goAliPay(String roomsno, Model model){
        // 获取我的订单，向前端传回我的订单信息  roomOrdersService
        return "Utils/alipayindex";
    }
    @PostMapping("/create")
    public String create(String id, String price, String title, Model model){
        String pay = alipayUtil.pay(id, price, title);
        model.addAttribute("form", pay);
        return "Utils/pay";
    }
    @GetMapping("/return")
    public String returnNotice(String out_trade_no, Model model){
//        String query = alipayUtil.query(out_trade_no);
//        model.addAttribute("query", query);
        return "user/roomorders";
    }
    @PostMapping("/notify")
    public void notifyUrl(String trade_no, String total_amount, String trade_status){
        System.err.println("支付宝订单编号：" + trade_no + ", 订单金额： " + total_amount + ",订单状态：" + trade_status);
    }




    // 房间预定界面初始化
    @RequestMapping("goReserveRoom")
    public String goReserveRoom(String roomsno, Model model){
        // 获取我的订单，向前端传回我的订单信息  roomOrdersService
        model.addAttribute("rooms",roomsService.roomsList(roomsno));
        return "user/roomorders";
    }
    // 房间预定
    @RequestMapping("reserveRoom")
    public String ReserveRoom(Roomorders roomOrders, HttpSession session, Integer num, Model model){
        // 1、时间戳作为订单编号
        roomOrders.setOrdercode(new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()));
        // 2、从session中获取到用户ID
        Users users = (Users) session.getAttribute("user");
        roomOrders.setUsersid(users.getUsersid());
        // 4、时间戳生成添加时间
        Date currentDate = new Date();
        roomOrders.setAddtime(currentDate);
        // 5、默认设置status为未入住
        roomOrders.setStatus("未入住");
        roomOrders.setMemo("未回复");
        Date start = roomOrders.getThestart();
        Date end = roomOrders.getTheend();
        Instant date1=start.toInstant();
        Instant date2=end.toInstant();
        long days= Duration.between(date1,date2).toDays();
        double total = roomsService.selectById(roomOrders.getRoomsid()).getPrice() * num * days;
        roomOrders.setTotal(total);
        model.addAttribute("isReserve",roomOrderService.roomordersAdd(roomOrders));
        model.addAttribute("code",roomOrders.getOrdercode());
        model.addAttribute("total",roomOrders.getTotal());
        model.addAttribute("title",roomOrders.getRoomordersid());
        return "Utils/alipayindex";
    }



    // 食品预定界面初始化
    @RequestMapping("goReserveFood")
    public String goReserveFood(String foodsname,double price, Model model){
        // 获取我的订单，向前端传回我的订单信息  roomOrdersService
        List<Foods> foods =foodsService.foodsList(foodsname);
        model.addAttribute("price",price);
        model.addAttribute("foods",foods);
        return "user/orders";
    }
    // 食品预定
    @RequestMapping("reserveFood")
    public String ReserveFood(Orders orders, HttpSession session, Integer num, Model model, double price){
        // 1、时间戳作为订单编号
        orders.setOrdercode(new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()));
        // 2、从session中获取到用户ID
        Users users = (Users) session.getAttribute("user");
        orders.setUsersid(users.getUsersid());
        // 3、雇员id默认为0
        orders.setEmployid(1);
        // 4、时间戳生成添加时间
        Date currentDate = new Date();
        orders.setAddtime(currentDate);
        // 5、默认设置status为未确认
        orders.setStatus("未确认");
        // 6、默认设置tabxid为0
        orders.setTabxid(1);
        // 7、求出total
        orders.setTotal(price*num);
        ordersService.ordersAdd(orders);
        model.addAttribute("code",orders.getOrdercode());
        model.addAttribute("total",orders.getTotal());
        model.addAttribute("title",orders.getOrdersid());
        return "Utils/alipayindex";
    }
}
