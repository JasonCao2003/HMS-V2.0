package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Booking;
import com.xupt.hms.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BookingController {

    @Resource
    private BookingService bookingService;


    // 增加 - 跳转至修改界面
    @RequestMapping("goAddBooking")
    public String goAddBooking() {
        return "admin/finance/addBooking";
    }

    // 增加
    @RequestMapping("addBooking")
    public String addUser(Booking booking, Model model) {
        model.addAttribute("isAdd", bookingService.bookingAdd(booking));
        return "admin/finance/addBooking";
    }

    // 删除
    @RequestMapping("deleteBooking")
    public String bookingDelete(Integer id, Model model) {
        model.addAttribute("isDelete", bookingService.bookingDelete(id));
        return "forward:listBookings";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectBooking")
    public String bookingSelect(Integer id, Model model) {
        model.addAttribute("booking", bookingService.selectById(id));
        return "/admin/finance/modifyBooking";
    }

    // 修改
    @RequestMapping("modifyBooking")
    public String bookingModify(Booking booking, Model model) {
        model.addAttribute("isModify", bookingService.bookingUpdate(booking));
        model.addAttribute("booking", booking);
        return "/admin/finance/modifyBooking";
    }

    // 查询 - 页面渲染
    @RequestMapping("listBookings")
    public String bookingList(Model model,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "start", defaultValue = "1") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "bookingid asc");
        // 2、从后端获取数据
        List<Booking> bookings = bookingService.bookingList(name);
        // 3、对数据分页
        PageInfo<Booking> page = new PageInfo<>(bookings);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("bookings", bookings);
        // 5、对下面视图进行渲染
        return "admin/finance/booking";
    }
}
