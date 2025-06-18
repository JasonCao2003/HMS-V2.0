package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Rooms;
import com.xupt.hms.service.RoomsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RoomsController {

    @Resource
    private RoomsService roomsService;

    @Value("${photo.file.dir}")
    private String photoPath;

    // 增加 - 跳转至修改界面
    @RequestMapping("goAddRooms")
    public String goAddRooms() {
        return "admin/goods/addRooms";
    }

    // 增加
    @RequestMapping("addRooms")
    public String addRooms(Rooms rooms, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        if (!photo.isEmpty()) {
            String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
            String originalFilename = photo.getOriginalFilename();
            String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filenamePrefix + filenameSuffix;
            // 2、上传文件
            photo.transferTo(new File(photoPath, newFilename));
            // 3、将图片文件名保存进数据库中
            rooms.setImage(newFilename);
        }
        model.addAttribute("isAdd", roomsService.roomsAdd(rooms));
        return "admin/goods/addRooms";
    }

    // 删除
    @RequestMapping("deleteRooms")
    public String roomsDelete(Integer id, Model model) {
        model.addAttribute("isDelete", roomsService.roomsDelete(id));
        return "forward:listRooms";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectRooms")
    public String roomsSelect(Integer id, Model model) {
        model.addAttribute("rooms", roomsService.selectById(id));
        return "admin/goods/modifyRooms";
    }

    // 修改
    @RequestMapping("modifyRooms")
    public String roomsModify(Rooms rooms, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        if (!photo.isEmpty()) {
            String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
            String originalFilename = photo.getOriginalFilename();
            String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filenamePrefix + filenameSuffix;
            // 2、上传文件
            photo.transferTo(new File(photoPath, newFilename));
            // 3、将图片文件名保存进数据库中
            rooms.setImage(newFilename);
        }
        System.out.println(rooms);
        model.addAttribute("isModify", roomsService.roomsUpdate(rooms));
        model.addAttribute("rooms", rooms);
        return "admin/goods/modifyRooms";
    }

    // 查询 - 页面渲染
    @RequestMapping("listRooms")
    public String roomsList(Model model,
                            @RequestParam(value = "roomsno", defaultValue = "") String roomsno,
                            @RequestParam(value = "start", defaultValue = "1") int start,
                            @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "roomsid asc");
        // 2、从后端获取数据
        List<Rooms> roomss = roomsService.roomsList(roomsno);
        // 3、对数据分页
        PageInfo<Rooms> page = new PageInfo<>(roomss);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("roomss", roomss);
        // 5、对下面视图进行渲染
        return "admin/goods/rooms";
    }
}
