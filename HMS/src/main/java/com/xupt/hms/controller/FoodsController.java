package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Foods;
import com.xupt.hms.service.FoodsService;
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
public class FoodsController {

    @Resource
    private FoodsService foodsService;

    @Value("${photo.file.dir}")
    private String photoPath;

    // 增加 - 跳转至修改界面
    @RequestMapping("goAddFoods")
    public String goAddFoods() {
        return "admin/goods/addFoods";
    }

    // 增加
    @RequestMapping("addFoods")
    public String addFoods(Foods foods, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        if (!photo.isEmpty()) {
            String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
            String originalFilename = photo.getOriginalFilename();
            String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filenamePrefix + filenameSuffix;
            // 2、上传文件
            photo.transferTo(new File(photoPath, newFilename));
            // 3、将图片文件名保存进数据库中
            foods.setImage(newFilename);
        }
        model.addAttribute("isAdd", foodsService.foodsAdd(foods));
        System.out.println(foods);
        return "admin/goods/addFoods";
    }

    // 删除
    @RequestMapping("deleteFoods")
    public String buyDelete(Integer id, Model model) {
        model.addAttribute("isDelete", foodsService.foodsDelete(id));
        return "forward:listFoods";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectFoods")
    public String foodsSelect(Integer id, Model model) {
        model.addAttribute("foods", foodsService.selectById(id));
        return "admin/goods/modifyFoods";
    }

    // 修改
    @RequestMapping("modifyFoods")
    public String foodsModify(Foods foods, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        if (!photo.isEmpty()) {
            String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
            String originalFilename = photo.getOriginalFilename();
            String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filenamePrefix + filenameSuffix;
            // 2、上传文件
            photo.transferTo(new File(photoPath, newFilename));
            // 3、将图片文件名保存进数据库中
            foods.setImage(newFilename);
        }
        System.out.println(foods);
        model.addAttribute("isModify", foodsService.foodsUpdate(foods));
        model.addAttribute("foods", foods);
        return "admin/goods/modifyFoods";
    }

    // 查询 - 页面渲染
    @RequestMapping("listFoods")
    public String foodsList(Model model,
                            @RequestParam(value = "foodsname", defaultValue = "") String foodsname,
                            @RequestParam(value = "start", defaultValue = "1") int start,
                            @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "foodsid asc");
        // 2、从后端获取数据
        List<Foods> foodss = foodsService.foodsList(foodsname);
        // 3、对数据分页
        PageInfo<Foods> page = new PageInfo<>(foodss);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("foodss", foodss);
        // 5、对下面视图进行渲染
        return "admin/goods/foods";
    }
}
